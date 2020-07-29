/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.exception;

import com.evrythng.api.wrapper.core.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#UNAUTHORIZED} (401)
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class UnauthorizedException extends EvrythngErrorException {

	public UnauthorizedException(ErrorMessage message) {
		super(message);
	}

	public UnauthorizedException(ErrorMessage message, Throwable cause) {
		super(message, cause);
	}
}
