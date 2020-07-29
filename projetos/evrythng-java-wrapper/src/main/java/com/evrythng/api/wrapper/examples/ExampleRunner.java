/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.examples;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.exception.EvrythngException;

/**
 * TODO: comment this class
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public abstract class ExampleRunner {

	protected ApiConfiguration config;

	/**
	 * @param config
	 */
	public ExampleRunner(ApiConfiguration config) {
		this.config = config;
	}

	public void run() throws EvrythngException {
		try {
			// Delegate:
			doRun();
		} catch (EvrythngException e) {
			System.out.println("[EvrythngException] " + e.getMessage());
			throw e;
		}
	}

	abstract public void doRun() throws EvrythngException;
}
