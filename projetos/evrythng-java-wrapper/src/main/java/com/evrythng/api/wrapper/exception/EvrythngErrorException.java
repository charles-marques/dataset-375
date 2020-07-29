/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.exception;

import com.evrythng.api.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * TODO Comment this class
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public abstract class EvrythngErrorException extends EvrythngException {

	private static final long serialVersionUID = 1L;
	private final ErrorMessage errorMessage;

	public EvrythngErrorException(ErrorMessage message) {
		this(message, null);
	}

	public EvrythngErrorException(ErrorMessage message, Throwable cause) {
		super(JSONUtils.write(message), cause);
		this.errorMessage = message;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
}
