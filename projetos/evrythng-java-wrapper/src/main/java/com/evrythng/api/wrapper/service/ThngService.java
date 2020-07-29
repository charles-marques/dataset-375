package com.evrythng.api.wrapper.service;

import java.util.Arrays;
import java.util.List;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.AbstractApiService;
import com.evrythng.api.wrapper.core.ApiBuilder.Builder;
import com.evrythng.api.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.Location;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.PropertyValue;
import com.evrythng.thng.resource.model.store.Thng;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Service warpper for the {@code /thngs} endpoint of the EVRYTHNG API.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ThngService extends AbstractApiService {

	public static final String PATH_THNGS = "/thngs";
	public static final String PATH_THNGS_BULK = PATH_THNGS + "/bulk";
	public static final String PATH_THNG = PATH_THNGS + "/%s";
	public static final String PATH_THNG_PROPERTIES = PATH_THNG + "/properties";
	public static final String PATH_THNG_PROPERTY = PATH_THNG_PROPERTIES + "/%s";
	public static final String PATH_THNG_LOCATION = PATH_THNG + "/location";

	public ThngService(ApiConfiguration config) {
		super(config);
	}

	/* ***** Thng ***** */

	/**
	 * POST {@value #PATH_THNGS}
	 * 
	 * @param thng
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Thng> thngCreator(Thng thng) throws EvrythngClientException {
		return post(PATH_THNGS, thng, new TypeReference<Thng>() {
		});
	}

	/**
	 * POST {@value #PATH_THNGS_BULK}
	 * 
	 * @param thngs
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<String>> thngsCreator(List<Thng> thngs) throws EvrythngClientException {
		return put(PATH_THNGS_BULK, thngs, new TypeReference<List<String>>() {
		});
	}

	/**
	 * GET {@value #PATH_THNGS}
	 * 
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Thng>> thngsReader() throws EvrythngClientException {
		return get(PATH_THNGS, new TypeReference<List<Thng>>() {
		});
	}

	/* ***** /thngs/{id} ***** */

	/**
	 * GET {@value #PATH_THNG}
	 * 
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Thng> thngReader(String thngId) throws EvrythngClientException {
		return get(String.format(PATH_THNG, thngId), new TypeReference<Thng>() {
		});
	}

	/**
	 * PUT {@value #PATH_THNG}
	 * 
	 * @param thngId
	 * @param thng
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Thng> thngUpdater(String thngId, Thng thng) throws EvrythngClientException {
		return put(String.format(PATH_THNG, thngId), thng, new TypeReference<Thng>() {
		});
	}

	/**
	 * DELETE {@value #PATH_THNG}
	 * 
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> thngDeleter(String thngId) throws EvrythngClientException {
		return delete(String.format(PATH_THNG, thngId));
	}

	/* ***** /thngs/{id}/properties ***** */

	/**
	 * PUT {@value #PATH_THNG_PROPERTIES}
	 * 
	 * @see #thngsCreator(List)
	 * @param thngId
	 * @param property
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertiesCreator(String thngId, Property property) throws EvrythngClientException {
		return propertiesCreator(thngId, Arrays.asList(property));
	}

	/**
	 * PUT {@value #PATH_THNG_PROPERTIES}
	 * 
	 * @param thngId
	 * @param properties
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertiesCreator(String thngId, List<Property> properties) throws EvrythngClientException {
		return put(String.format(PATH_THNG_PROPERTIES, thngId), properties, new TypeReference<List<Property>>() {
		});
	}

	/**
	 * GET {@value #PATH_THNG_PROPERTIES}
	 * 
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertiesReader(String thngId) throws EvrythngClientException {
		return get(String.format(PATH_THNG_PROPERTIES, thngId), new TypeReference<List<Property>>() {
		});
	}

	/**
	 * DELETE {@value #PATH_THNG_PROPERTIES}
	 * 
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> propertiesDeleter(String thngId) throws EvrythngClientException {
		return delete(String.format(PATH_THNG_PROPERTIES, thngId));
	}

	/* ***** /thngs/{id}/properties/{key} ***** */

	/**
	 * GET {@value #PATH_THNG_PROPERTY}
	 * 
	 * @param thngId
	 * @param key
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<PropertyValue>> propertyReader(String thngId, String key) throws EvrythngClientException {
		return get(String.format(PATH_THNG_PROPERTY, thngId, key), new TypeReference<List<PropertyValue>>() {
		});
	}

	/**
	 * PUT {@value #PATH_THNG_PROPERTY}
	 * 
	 * @see #propertiesCreator(String, Property)
	 * @param thngId
	 * @param key
	 * @param value
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<PropertyValue>> propertyUpdater(String thngId, String key, String value) throws EvrythngClientException {
		return propertyUpdater(thngId, key, new PropertyValue(value));
	}

	/**
	 * PUT {@value #PATH_THNG_PROPERTY}
	 * 
	 * @see #propertiesCreator(String, Property)
	 * @param thngId
	 * @param key
	 * @param value
	 * @param timestamp
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<PropertyValue>> propertyUpdater(String thngId, String key, String value, long timestamp) throws EvrythngClientException {
		return propertyUpdater(thngId, key, new PropertyValue(value, timestamp));
	}

	/**
	 * PUT {@value #PATH_THNG_PROPERTY}
	 * 
	 * @param thngId
	 * @param key
	 * @param value
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<PropertyValue>> propertyUpdater(String thngId, String key, PropertyValue value) throws EvrythngClientException {
		return put(String.format(PATH_THNG_PROPERTY, thngId, key), Arrays.asList(value), new TypeReference<List<PropertyValue>>() {
		});
	}

	/**
	 * DELETE {@value #PATH_THNG_PROPERTY}
	 * 
	 * @param thngId
	 * @param key
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> propertyDeleter(String thngId, String key) throws EvrythngClientException {
		return delete(String.format(PATH_THNG_PROPERTY, thngId, key));
	}

	/* ***** /thngs/{id}/location ***** */

	/**
	 * GET {@value #PATH_THNG_LOCATION}
	 * 
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Location>> locationReader(String thngId) throws EvrythngClientException {
		return get(String.format(PATH_THNG_LOCATION, thngId), new TypeReference<List<Location>>() {
		});
	}

	/**
	 * PUT {@value #PATH_THNG_LOCATION}
	 * 
	 * @see #locationUpdater(String, List)
	 * @param thngId
	 * @param location
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Location>> locationUpdater(String thngId, Location location) throws EvrythngClientException {
		return locationUpdater(thngId, Arrays.asList(location));
	}

	/**
	 * PUT {@value #PATH_THNG_LOCATION}
	 * 
	 * @param thngId
	 * @param locations
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Location>> locationUpdater(String thngId, List<Location> locations) throws EvrythngClientException {
		return put(String.format(PATH_THNG_LOCATION, thngId), locations, new TypeReference<List<Location>>() {
		});
	}

	/**
	 * DELETE {@value #PATH_THNG_LOCATION}
	 * 
	 * @param thngId
	 * @param key
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> locationDeleter(String thngId) throws EvrythngClientException {
		return delete(String.format(PATH_THNG_LOCATION, thngId));
	}
}
