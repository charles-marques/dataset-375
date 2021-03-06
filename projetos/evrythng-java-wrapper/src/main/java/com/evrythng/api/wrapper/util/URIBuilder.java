/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.evrythng.api.wrapper.exception.EvrythngClientException;

/**
 * 
 * TODO Comment this class
 * 
 * @author Thomas Pham (tpham)
 * @copyright 2012 Evrythng Ltd London / Zurich
 **/

public class URIBuilder {

	private final String baseUri;

	private MultiValueMap<String, String> parameters;

	private URIBuilder(String baseUri) {
		this.baseUri = baseUri;
		parameters = new LinkedMultiValueMap<String, String>();
	}

	/**
	 * Creates a URIBuilder with a base URI string as the starting point
	 */
	public static URIBuilder fromUri(String baseUri) {
		return new URIBuilder(baseUri);
	}

	/**
	 * Adds a query parameter to the URI
	 */
	public URIBuilder queryParam(String name, String value) {
		parameters.add(name, value);
		return this;
	}

	/**
	 * Adds a query parameters to the URI
	 */
	public URIBuilder queryParams(MultiValueMap<String, String> params) {
		parameters.putAll(params);
		return this;
	}

	/**
	 * Builds the URI
	 * 
	 * @throws EvrythngClientException
	 */
	public URI build() throws EvrythngClientException {
		try {
			StringBuilder builder = new StringBuilder();
			Set<Entry<String, List<String>>> entrySet = parameters.entrySet();
			for (Iterator<Entry<String, List<String>>> entryIt = entrySet.iterator(); entryIt.hasNext();) {
				Entry<String, List<String>> entry = entryIt.next();
				String name = entry.getKey();
				List<String> values = entry.getValue();
				for (Iterator<String> valueIt = values.iterator(); valueIt.hasNext();) {
					String value = valueIt.next();
					builder.append(formEncode(name)).append("=");
					if (value != null) {
						builder.append(formEncode(value));
					}
					if (valueIt.hasNext()) {
						builder.append("&");
					}
				}
				if (entryIt.hasNext()) {
					builder.append("&");
				}
			}

			String queryDelimiter = "?";
			if (URI.create(baseUri).getQuery() != null) {
				queryDelimiter = "&";
			}
			return new URI(baseUri + (builder.length() > 0 ? queryDelimiter + builder.toString() : ""));
		} catch (URISyntaxException e) {
			// Convert to custom exception:
			throw new EvrythngClientException("Unable to build URI: Bad URI syntax", e);
		}
	}

	private String formEncode(String data) {
		try {
			return URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException wontHappen) {
			throw new IllegalStateException(wontHappen);
		}
	}
}
