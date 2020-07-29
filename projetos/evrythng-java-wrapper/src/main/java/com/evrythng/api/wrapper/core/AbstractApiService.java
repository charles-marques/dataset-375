package com.evrythng.api.wrapper.core;

import java.net.URI;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.ApiBuilder.Builder;
import com.evrythng.api.wrapper.exception.EvrythngClientException;
import com.evrythng.api.wrapper.util.URIBuilder;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * TODO: comment this class
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public abstract class AbstractApiService {

	protected ApiConfiguration config;

	public AbstractApiService(ApiConfiguration config) {
		this.config = config;
	}

	protected <T> Builder<T> post(String relativePath, Object data, TypeReference<T> type) throws EvrythngClientException {
		return ApiBuilder.post(config.getKey(), absoluteUri(relativePath), data, Status.CREATED, type);
	}

	protected <T> Builder<T> get(String relativePath, TypeReference<T> type) throws EvrythngClientException {
		return ApiBuilder.get(config.getKey(), absoluteUri(relativePath), Status.OK, type);
	}

	protected <T> Builder<T> put(String relativePath, Object data, TypeReference<T> type) throws EvrythngClientException {
		return ApiBuilder.put(config.getKey(), absoluteUri(relativePath), data, Status.OK, type);
	}

	protected Builder<Boolean> delete(String relativePath) throws EvrythngClientException {
		return ApiBuilder.delete(config.getKey(), absoluteUri(relativePath), Status.OK);
	}

	protected URI absoluteUri(String relativePath) throws EvrythngClientException {
		if (!relativePath.startsWith("/")) {
			relativePath = String.format("/%s", relativePath);
		}
		return URIBuilder.fromUri(String.format("%s%s", config.getUrl(), relativePath)).build();
	}
}
