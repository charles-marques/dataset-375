/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * JSON utilities based on Jackson {@link ObjectMapper}.
 * 
 * @author Dominique Guinard (dom)
 * @author Pedro De Almeida (almeidap)
 **/
public final class JSONUtils {

	/**
	 * ObjectMapper singleton.
	 */
	private static ObjectMapper MAPPER = null;

	private JSONUtils() {
		/* Hide default constructor */
	}

	/**
	 * Deserializes the provided {@code json} {@link String} to a native {@code type} representation.
	 * 
	 * @param json
	 * @param type
	 * @return Deserialized native instance
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static <T> T read(String json, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		try {
			return getObjectMapper().readValue(json, type);
		} catch (Exception e) {
			// Convert to unchecked exception:
			throw new RuntimeException(e);
		}
	}

	/**
	 * Deserializes the provided {@code json} {@link String} to a native {@code type} representation.
	 * 
	 * @param json
	 * @param type
	 * @return Deserialized native instance
	 */
	public static <T> T read(String json, TypeReference<T> type) {
		try {
			return getObjectMapper().readValue(json, type);
		} catch (Exception e) {
			// Convert to unchecked exception:
			throw new RuntimeException(e);
		}
	}

	/**
	 * Deserializes the provided {@link InputStream} to a native {@code type} representation.
	 * 
	 * @param inputStream
	 * @param type
	 * @return Deserialized native instance
	 */
	public static <T> T read(InputStream inputStream, TypeReference<T> type) throws JsonParseException, JsonMappingException, IOException {
		try {
			return getObjectMapper().readValue(inputStream, type);
		} catch (Exception e) {
			// Convert to unchecked exception:
			throw new RuntimeException(e);
		}
	}

	/**
	 * Deserializes the provided {@link InputStream} to a native {@code valueType} representation.
	 * 
	 * @param inputStream
	 * @param type
	 * @return Deserialized native instance
	 */
	public static <T> T read(InputStream inputStream, Class<T> valueType) {
		try {
			return getObjectMapper().readValue(inputStream, valueType);
		} catch (Exception e) {
			// Convert to unchecked exception:
			throw new RuntimeException(e);
		}
	}

	/**
	 * Converts the provided {@code object} to an JSON {@link String}.
	 * 
	 * @param object
	 * @return
	 */
	public static String write(Object object) {
		try {
			return getObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			// Convert to unchecked exception:
			throw new RuntimeException(e);
		}
	}

	/**
	 * Create an {@link ObjectMapper} and register all of the custom types
	 * needed
	 * in order to properly deserialize complex evrythng-specific types.
	 * 
	 * @return Assembled Jackson mapper instance.
	 */
	public static ObjectMapper getObjectMapper() {
		if (MAPPER == null) {
			MAPPER = createObjectMapper();
		}
		return MAPPER;
	}

	private static ObjectMapper createObjectMapper() {

		ObjectMapper mapper = new ObjectMapper();
		SimpleModule evrythngModule = new SimpleModule("evrythng", new Version(3, 0, 0, "SNAPSHOT"));

		final DateFormat dateFormat = new SimpleDateFormat("SSSSSS"); // milliseconds
		evrythngModule.addDeserializer(Calendar.class, new JsonDeserializer<Calendar>() {
			@Override
			public Calendar deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
				try {
					Calendar c = Calendar.getInstance();
					c.setTime(dateFormat.parse(arg0.getText()));
					return c;
				} catch (ParseException e) {
					return null;
				}
			}
		});

		mapper.registerModule(evrythngModule);
		return mapper;
	}
}
