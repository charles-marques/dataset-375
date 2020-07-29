/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.exception;

import com.evrythng.api.wrapper.core.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#NOT_FOUND} (404)
 * 
 * @author Thomas Pham (tpham)
 * @author Pedro De Almeida (almeidap)
 **/
public class NotFoundException extends EvrythngErrorException {

	public NotFoundException(ErrorMessage message) {
		super(message);
	}

	public NotFoundException(ErrorMessage message, Throwable cause) {
		super(message, cause);
	}
}
