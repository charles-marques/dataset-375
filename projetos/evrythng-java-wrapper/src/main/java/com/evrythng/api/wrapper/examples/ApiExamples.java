/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.examples;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.exception.EvrythngException;

/**
 * Base definition for running examples using the EVRYTHNG API wrapper.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ApiExamples {

	/**
	 * @param args
	 * @throws EvrythngException
	 */
	public static void main(String[] args) throws EvrythngException {

		if (args.length <= 1) {
			usage();
			return;
		}

		ApiConfiguration config = extractConfig(args);

		// Run examples:
		new ThngApiExample(config).run();
		new ThngLocationApiExample(config).run();
		new ThngPropertyApiExample(config).run();
		new ExceptionHandlingExample(config).run();

		System.exit(0);
	}

	public static ApiConfiguration extractConfig(String[] args) {
		ApiConfiguration config = new ApiConfiguration();
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--key") && i + 1 < args.length) {
				config.setKey(args[i + 1]);
			} else if (args[i].equals("--url") && i + 1 < args.length) {
				config.setUrl(args[i + 1]);
			}
		}
		return config;
	}

	public static void usage() {
		System.out.println(">> Usage:");
		System.out.println("   --key: The EVRYTHNG API key for authentication.\n");
		System.out.println("   --url: [Optional] The EVRYTHNG API base URL.\n");
	}

}
