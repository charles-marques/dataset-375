package com.evrythng.api.wrapper;

import org.apache.commons.lang3.StringUtils;

import com.evrythng.api.wrapper.service.CollectionService;
import com.evrythng.api.wrapper.service.ThngService;

/**
 * Definition for API management.
 * 
 * @author tpham
 * @author Pedro De Almeida (almeidap)
 */
public class ApiManager {

	protected final ApiConfiguration config;

	private ThngService thngService;
	private CollectionService collectionService;

	/**
	 * Create a new manager instance.
	 */
	public ApiManager(ApiConfiguration config) {
		this.config = checkConfiguration(config);
		thngService = new ThngService(this.config);
		collectionService = new CollectionService(this.config);
	}

	public ApiManager(String apiKey) {
		this(new ApiConfiguration(apiKey));
	}

	/**
	 * Checks that the provided {@code apiConfig} is valid.
	 * 
	 * @param apiConfig
	 * @return
	 */
	protected static ApiConfiguration checkConfiguration(ApiConfiguration apiConfig) {
		if (StringUtils.isBlank(apiConfig.getUrl())) {
			throw new IllegalStateException(String.format("URL of provided API configuration is invalid: [url=%s]", apiConfig.getUrl()));
		}

		if (StringUtils.isBlank(apiConfig.getKey())) {
			throw new IllegalStateException(String.format("API key of provided API configuration is invalid: [key=%s]", apiConfig.getKey()));
		}

		return apiConfig;
	}

	public ThngService thngService() {
		return this.thngService;
	}

	public CollectionService collectionService() {
		return this.collectionService;
	}

	public ApiConfiguration getConfig() {
		return config;
	}

}
