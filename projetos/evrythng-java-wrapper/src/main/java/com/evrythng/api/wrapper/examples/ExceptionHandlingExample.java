/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.examples;

import java.util.List;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.ApiManager;
import com.evrythng.api.wrapper.core.ApiBuilder.Builder;
import com.evrythng.api.wrapper.exception.BadRequestException;
import com.evrythng.api.wrapper.exception.EvrythngException;
import com.evrythng.api.wrapper.exception.ForbiddenException;
import com.evrythng.api.wrapper.exception.NotFoundException;
import com.evrythng.api.wrapper.service.ThngService;
import com.evrythng.api.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.Thng;

/**
 * This is a simple example of how to use the EVRYTHNG API wrapper.
 * 
 * @author Dominique Guinard (domguinard)
 * 
 */
public class ExceptionHandlingExample extends ExampleRunner {

	/**
	 * @param config
	 */
	public ExceptionHandlingExample(ApiConfiguration config) {
		super(config);
	}

	/**
	 * @param args
	 * @throws EvrythngException
	 */
	public static void main(String[] args) throws EvrythngException {

		if (args.length <= 1) {
			ApiExamples.usage();
			return;
		}

		ApiConfiguration config = ApiExamples.extractConfig(args);

		// Run example:
		new ExceptionHandlingExample(config).run();

		System.exit(0);
	}

	/*
	 * (non-Javadoc)
	 * @see com.evrythng.api.wrapper.examples.ExampleRunner#doRun()
	 */
	@Override
	public void doRun() throws EvrythngException {
		// Initialize the API Manager:
		ApiManager apiManager = new ApiManager(config);

		// Get the Thng API service.
		ThngService thngService = apiManager.thngService();

		// Command builder: GET /thngs:
		Builder<List<Thng>> thngsReader = thngService.thngsReader();

		List<Thng> results = thngsReader.execute();
		System.out.println("GET /thngs: " + JSONUtils.write(results));
		System.out.println("GET /thngs > count: " + thngsReader.count());

		Thng thng = new Thng();
		Builder<Thng> thngCreator = thngService.thngCreator(thng);

		try {
			thngCreator.execute();
		} catch (BadRequestException e) {
			System.out.println("POST /thngs: [BadRequestException]");
		}

		thng.setName("foo");
		Thng created = thngService.thngCreator(thng).execute();

		// Command builder: DELETE /thngs/{id}:
		boolean deleted = thngService.thngDeleter(created.getId()).execute();
		System.out.println("DELETE /thngs/{id}: " + deleted);
		System.out.println("GET /thngs > count: " + thngsReader.count());

		Builder<Thng> thngReader = thngService.thngReader(created.getId());
		try {
			thngReader.execute();
		} catch (NotFoundException e) {
			System.out.println("GET /thngs/{id}: [NotFoundException]");
		}

		try {
			thngReader.apiKey("invalid").execute();
		} catch (ForbiddenException e) {
			System.out.println("GET /thngs/{id}: [ForbiddenException]");
		}

		System.out.println("GET /thngs > count: " + thngsReader.count());
	}
}
