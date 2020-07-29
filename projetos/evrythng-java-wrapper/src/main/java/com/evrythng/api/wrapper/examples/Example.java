/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.examples;

import com.evrythng.api.wrapper.ApiManager;
import com.evrythng.api.wrapper.service.CollectionService;
import com.evrythng.api.wrapper.service.ThngService;

/**
 * This is a simple example of how to use the EVRYTHNG API wrapper.
 * 
 * @author Dominique Guinard (domguinard)
 * 
 */
public class Example {
	// private static String EVRYTHNG_ROOT_URL = "URL_HERE";

	private static final String ACCESS_TOKEN = "YOUR_API_TOKEN_HERE";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Instantiate the API Manager
		ApiManager apiManager = new ApiManager(ACCESS_TOKEN);

		ThngService thngService = apiManager.thngService();
		CollectionService collectionService = apiManager.collectionService();
		/*
		// Create a new Thng
		Thng aThng = new Thng();
		aThng.setName("Test from Android!");
		aThng.setDescription("Hello Android World!");

		// call the service
		// https://api.evrythng.com/thngs
		aThng = thngService.createThng(aThng);
		System.out.println("Created thng: " + aThng.getCreatedAt());

		// Get the latest property value
		// https://api.evrythng.com/thngs/{thngId}/properties/{key}
		PropertyValue productName = thngService.getLatestThngPropertyValue(aThng.getId(), "ProductName");
		System.out.println(productName); // PropertyValue ovveride toString method to return the value directly

		// Get a collection
		// http://api.evrythng.com/collections/4fdf2efe0b1cdc01700001e7
		Collection collection = collectionService.getCollectionById("4fdf2efe0b1cdc01700001e7");
		System.out.println(collection.getDescription());

		// Add thng to collection
		List<String> thngIds = new ArrayList<String>();
		thngIds.add("4fdf251c0b1cdc017400009d");
		List<String> updatedThngIdsOfCollection = collectionService.updateThngIdsOfCollectionId("4fdf2efe0b1cdc01700001e7", thngIds);
		System.out.println(updatedThngIdsOfCollection.toArray());

		// Update time of a property value
		productName = thngService.pushThngPropertyValueByKeyAt(aThng.getId(), "KEY", "VALUE", Calendar.getInstance().getTimeInMillis());
		System.out.println(productName.getTimestamp());
		*/
	}

}
