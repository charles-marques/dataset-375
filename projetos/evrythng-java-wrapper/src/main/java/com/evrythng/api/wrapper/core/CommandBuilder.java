/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.core;

import java.net.URI;

import org.apache.http.HttpResponse;

import com.evrythng.api.wrapper.core.HttpMethodBuilder.MethodBuilder;
import com.evrythng.api.wrapper.exception.EvrythngException;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Generic API command builder.
 * 
 * @author Pedro De Almeida (almeidap)
 **/
@SuppressWarnings("rawtypes")
public class CommandBuilder<T, B extends CommandBuilder> {

	protected ApiCommand<T> command;

	public CommandBuilder(String apiKey, MethodBuilder<?> methodBuilder, URI uri, Status responseStatus, TypeReference<T> responseType) {
		this.command = new ApiCommand<T>(apiKey, methodBuilder, uri, responseStatus, responseType);
	}

	@SuppressWarnings("unchecked")
	public B queryParam(String name, String value) {
		command.setQueryParam(name, value);
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B header(String key, String value) {
		command.setHeader(key, value);
		return (B) this;
	}

	/**
	 * @see ApiCommand#execute()
	 * @return
	 * @throws EvrythngException
	 */
	public T execute() throws EvrythngException {
		return command.execute();
	}

	/**
	 * Executes the current command and wraps the {@link HttpResponse} entity into a JSONP callback.
	 * 
	 * @param callback The name of the callback function
	 * @see ApiCommand#jsonp(String)
	 * @throws EvrythngException
	 */
	public String jsonp(String callback) throws EvrythngException {
		return command.jsonp(callback);
	}

	/**
	 * Counts the <strong>total</strong> number of elements if the current command was executed as a GET request.
	 * 
	 * @return
	 * @throws EvrythngException
	 */
	public int count() throws EvrythngException {
		return command.count();
	}
}
