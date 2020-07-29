/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.exception;

import com.evrythng.api.wrapper.core.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#FORBIDDEN} (403)
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class ForbiddenException extends EvrythngErrorException {

	public ForbiddenException(ErrorMessage message) {
		super(message);
	}

	public ForbiddenException(ErrorMessage message, Throwable cause) {
		super(message, cause);
	}
}
