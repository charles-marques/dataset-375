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
import com.evrythng.api.wrapper.exception.EvrythngException;
import com.evrythng.api.wrapper.service.ThngService;
import com.evrythng.api.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.Thng;

/**
 * This is a simple example of how to use the EVRYTHNG API wrapper.
 * 
 * @author Dominique Guinard (domguinard)
 * 
 */
public class ThngApiExample extends ExampleRunner {

	/**
	 * @param config
	 */
	public ThngApiExample(ApiConfiguration config) {
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
		new ThngApiExample(config).run();

		System.exit(0);
	}

	/* (non-Javadoc)
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

		results = thngsReader.page(2).execute();
		System.out.println("GET /thngs?page=2: " + JSONUtils.write(results));

		results = thngsReader.page(2).perPage(2).execute();
		System.out.println("GET /thngs?page=2&perPage=2: " + JSONUtils.write(results));

		results = thngsReader.perPage(1).execute();
		System.out.println("GET /thngs?perPage=1: " + JSONUtils.write(results));

		// Command builder: GET /thngs/{id}:
		Thng retrieved = thngService.thngReader(results.get(0).getId()).execute();
		System.out.println("GET /thngs/{id}: " + JSONUtils.write(retrieved));

		// Command builder: POST /thngs:
		retrieved.setId(null);
		Builder<Thng> thngCreator = thngService.thngCreator(retrieved);

		Thng created = thngCreator.execute();
		System.out.println("POST /thngs: " + JSONUtils.write(created));
		System.out.println("GET /thngs > count: " + thngsReader.count());

		// Command builder: POST /thngs/bulk:
		for (Thng thng : results) {
			thng.setId(null);
		}
		/*
		List<String> refs = thngService.thngsCreator(results).execute();
		System.out.println("POST /thngs/bulk: " + JSONUtils.write(refs));
		*/

		// Command builder: PUT /thngs:
		created.setName("[Updated] " + created.getName());
		Thng updated = thngService.thngUpdater(created.getId(), created).execute();
		System.out.println("PUT /thngs/{id}: " + JSONUtils.write(updated));
		System.out.println("GET /thngs > count: " + thngsReader.count());

		// Command builder: DELETE /thngs/{id}:
		created = thngCreator.execute();
		boolean deleted = thngService.thngDeleter(created.getId()).execute();
		System.out.println("DELETE /thngs/{id}: " + deleted);
		System.out.println("GET /thngs > count: " + thngsReader.count());
	}
}
