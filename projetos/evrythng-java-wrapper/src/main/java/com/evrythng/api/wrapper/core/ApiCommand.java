package com.evrythng.api.wrapper.core;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.HttpMethodBuilder.MethodBuilder;
import com.evrythng.api.wrapper.exception.BadRequestException;
import com.evrythng.api.wrapper.exception.ConflictException;
import com.evrythng.api.wrapper.exception.EvrythngClientException;
import com.evrythng.api.wrapper.exception.EvrythngException;
import com.evrythng.api.wrapper.exception.EvrythngUnexpectedException;
import com.evrythng.api.wrapper.exception.ForbiddenException;
import com.evrythng.api.wrapper.exception.InternalErrorException;
import com.evrythng.api.wrapper.exception.NotFoundException;
import com.evrythng.api.wrapper.exception.UnauthorizedException;
import com.evrythng.api.wrapper.util.JSONUtils;
import com.evrythng.api.wrapper.util.URIBuilder;
import com.evrythng.thng.resource.model.exception.ErrorMessage;
import com.fasterxml.jackson.core.type.TypeReference;

public class ApiCommand<T> {

	private static final Logger logger = LoggerFactory.getLogger(ApiCommand.class);

	protected LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
	protected Map<String, String> headers = new LinkedHashMap<String, String>();

	protected String apiKey;
	protected MethodBuilder<?> methodBuilder;
	protected URI uri;
	protected Status responseStatus;
	protected TypeReference<T> responseType;

	public ApiCommand(String apiKey, MethodBuilder<?> methodBuilder, URI uri, Status responseStatus, TypeReference<T> responseType) {
		this.apiKey = apiKey;
		this.methodBuilder = methodBuilder;
		this.uri = uri;
		this.responseStatus = responseStatus;
		this.responseType = responseType;
	}

	/**
	 * Executes the current command and maps the {@link HttpResponse} entity to {@code T} specified by
	 * {@link ApiCommand#responseType}.
	 * 
	 * @see #execute(TypeReference)
	 * @return
	 * @throws EvrythngException
	 */
	public T execute() throws EvrythngException {
		return execute(responseType);
	}

	/**
	 * Executes the current command and wraps the {@link HttpResponse} entity into a JSONP callback.
	 * 
	 * @param callback The name of the callback function
	 * @throws EvrythngException
	 * @return
	 */
	public String jsonp(String callback) throws EvrythngException {
		setQueryParam(ApiConfiguration.QUERY_PARAM_CALLBACK, callback);
		String jsonp = execute(new TypeReference<String>() {
		});
		removeQueryParam(ApiConfiguration.QUERY_PARAM_CALLBACK);
		return jsonp;
	}

	/*
	 * FIXME: check usefulness & validity of this!
	 */
	public int count() throws EvrythngException {
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = execute(client, new HttpGet(buildUri()), Status.OK);
			Header xResultCountHeader = this.getHttpHeader(response, ApiConfiguration.HTTP_HEADER_RESULT_COUNT);
			return Integer.valueOf(xResultCountHeader.getValue());
		} finally {
			shutdown(client);
		}
	}

	public void setHeader(String key, String value) {
		logger.debug("Setting header: [key={}, value={}]", key, value);
		headers.put(key, value);
	}

	/**
	 * @param name
	 * @param value
	 */
	public void setQueryParam(String key, String value) {
		logger.debug("Setting query parameter: [key={}, value={}]", key, value);
		queryParams.set(key, value);
	}

	/**
	 * @param name
	 */
	public void removeQueryParam(String key) {
		logger.debug("Removing query parameter: [key={}]", key);
		queryParams.remove(key);
	}

	/**
	 * @param request
	 */
	protected HttpUriRequest prepare(HttpUriRequest request) {
		// Define required API headers:
		request.setHeader("Content-Type", ApiConfiguration.HTTP_CONTENT_TYPE);
		request.setHeader("Accept", ApiConfiguration.HTTP_ACCEPT_TYPE);
		request.setHeader("Authorization", apiKey);

		// Define client headers:
		for (Entry<String, String> header : headers.entrySet()) {
			request.setHeader(header.getKey(), header.getValue());
		}

		return request;
	}

	protected URI buildUri() throws EvrythngClientException {
		return URIBuilder.fromUri(uri.toString()).queryParams(queryParams).build();
	}

	protected Header getHttpHeader(HttpResponse response, String name) {
		logger.debug("Retrieving HTTP header: [name={}]", name);
		return response.getFirstHeader(name);
	}

	/**
	 * 
	 * @param client
	 * @return
	 * @throws EvrythngException
	 */
	protected HttpResponse execute(HttpClient client) throws EvrythngException {
		// Build request method:
		HttpRequestBase request = methodBuilder.build(buildUri());

		// Delegate:
		return execute(client, request, responseStatus, new TypeReference<HttpResponse>() {
		});
	}

	private <K> K execute(TypeReference<K> type) throws EvrythngException {
		HttpClient client = new DefaultHttpClient();
		try {
			// Build request method:
			HttpRequestBase request = methodBuilder.build(buildUri());
			return execute(client, request, responseStatus, type);
		} finally {
			shutdown(client);
		}
	}

	private HttpResponse execute(HttpClient client, HttpRequestBase request, Status expectedStatus) throws EvrythngException {
		return execute(client, request, expectedStatus, new TypeReference<HttpResponse>() {
		});
	}

	@SuppressWarnings("unchecked")
	private <K> K execute(HttpClient client, HttpRequestBase request, Status expectedStatus, TypeReference<K> type) throws EvrythngException {
		logger.debug(">> Executing request: [method={}, url={}]", request.getMethod(), request.getURI().toString());
		K result = null;
		HttpResponse response = null;
		try {
			response = client.execute(prepare(request));
		} catch (Exception e) {
			// Convert to custom exception:
			throw new EvrythngClientException("Unable to execute request!", e);
		}

		// Retrieve response entity (as String so that it can be outputted in case of exception):
		String entity = readEntity(response);

		Status responseStatus = Status.fromStatusCode(response.getStatusLine().getStatusCode());
		if (responseStatus == null) {
			throw new EvrythngUnexpectedException(new ErrorMessage(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Unknown status code " + response.getStatusLine().getStatusCode()));
		}
		logger.debug("<< Response received: [code={}, expected={}]", responseStatus.getStatusCode(), expectedStatus.getStatusCode());

		if (!responseStatus.equals(expectedStatus)) {
			logger.debug("Unexpected status code, mapping response to ErrorMessage...");

			// Map to ErrorMessage:
			ErrorMessage message = null;
			try {
				// API should always return an ErrorMessage as JSON:
				message = JSONUtils.read(entity, new TypeReference<ErrorMessage>() {
				});
			} catch (Exception e) {
				throw new EvrythngClientException(String.format("Unable to retrieve ErrorMessage from response: [entity=%s]", entity), e);
			}

			// Handle unexpected status (TODO: handle all required status family and codes):
			switch (responseStatus.getFamily()) {
				case CLIENT_ERROR:
					switch (responseStatus) {
						case BAD_REQUEST:
							throw new BadRequestException(message);
						case UNAUTHORIZED:
							throw new UnauthorizedException(message);
						case FORBIDDEN:
							throw new ForbiddenException(message);
						case NOT_FOUND:
							throw new NotFoundException(message);
						case CONFLICT:
							throw new ConflictException(message);
						default:
							throw new EvrythngUnexpectedException(message);
					}
				case SERVER_ERROR:
					throw new InternalErrorException(message);
				default:
					throw new EvrythngUnexpectedException(message);
			}
		} else {
			if (type.getType().equals(HttpResponse.class)) {
				// We already have a HttpResponse, let's return it:
				result = (K) response;
			} else if (type.getType().equals(String.class)) {
				result = (K) entity;
			} else {
				try {
					result = JSONUtils.read(entity, type);
				} catch (Exception e) {
					throw new EvrythngClientException(String.format("Unable to map response entity: [type=%s, entity=%s]", type.getType(), entity), e);
				}
			}
		}
		return result;
	}

	/**
	 * Reads entity content from the provided {@link HttpResponse}.
	 * 
	 * @param response
	 * @return
	 * @throws EvrythngClientException
	 */
	private String readEntity(HttpResponse response) throws EvrythngClientException {
		HttpEntity entity = response.getEntity();
		logger.debug("Reading response entity...");

		String result = null;
		try {
			result = IOUtils.toString(entity.getContent());
		} catch (Exception e) {
			// Convert to custom exception:
			throw new EvrythngClientException(String.format("Error while reading response entity! [type=%s]", String.class), e);
		}
		return result;
	}

	/**
	 * Shuts down the connection manager to ensure immediate deallocation of all system resources.
	 * 
	 * @param client
	 */
	protected void shutdown(HttpClient client) {
		client.getConnectionManager().shutdown();
	}
}
