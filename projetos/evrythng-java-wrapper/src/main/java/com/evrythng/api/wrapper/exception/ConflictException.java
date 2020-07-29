/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.exception;

import com.evrythng.api.wrapper.core.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#CONFLICT} (409)
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class ConflictException extends EvrythngErrorException {

	public ConflictException(ErrorMessage message) {
		super(message);
	}

	public ConflictException(ErrorMessage message, Throwable cause) {
		super(message, cause);
	}
}
