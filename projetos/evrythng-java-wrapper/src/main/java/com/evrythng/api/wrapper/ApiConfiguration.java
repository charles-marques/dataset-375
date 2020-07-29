/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper;

/**
 * Parameters and values for the EVRYTHNG API configuration.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ApiConfiguration {

	/*
	 * EVRYTHNG API
	 */
	private String url = "http://api.evrythng.com";
	private String key = null;

	/*
	 * HTTP
	 */
	public static final String HTTP_ACCEPT_TYPE = "application/json";
	public static final String HTTP_CONTENT_TYPE = "application/json";
	public static final String HTTP_HEADER_RESULT_COUNT = "x-result-count";
	public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";

	/*
	 * Query Parameters
	 */
	public static final String QUERY_PARAM_SEARCH = "q";
	public static final String QUERY_PARAM_PAGE = "page";
	public static final String QUERY_PARAM_PER_PAGE = "perPage";
	public static final String QUERY_PARAM_FROM = "from";
	public static final String QUERY_PARAM_TO = "to";
	public static final String QUERY_PARAM_CALLBACK = "callback";

	public enum QueryKeyword {
		LATEST("latest");

		private String keyword;

		QueryKeyword(String keyword) {
			this.keyword = keyword;
		}

		/* {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return keyword;
		}
	}

	/*
	 * Default values
	 */
	private int perPage = 30;

	public ApiConfiguration() {
	}

	public ApiConfiguration(String apiKey) {
		this.key = apiKey;
	}

	public ApiConfiguration(String apiUrl, String apiKey) {
		this.url = apiUrl;
		this.key = apiKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
}
