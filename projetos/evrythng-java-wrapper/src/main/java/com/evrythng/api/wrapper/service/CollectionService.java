package com.evrythng.api.wrapper.service;

import java.util.Arrays;
import java.util.List;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.AbstractApiService;
import com.evrythng.api.wrapper.core.ApiBuilder.Builder;
import com.evrythng.api.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.Collection;
import com.evrythng.thng.resource.model.store.Thng;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Service warpper for the {@code /collections} endpoint of the EVRYTHNG API.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class CollectionService extends AbstractApiService {

	public static final String PATH_COLLECTIONS = "/collections";
	public static final String PATH_COLLECTION = PATH_COLLECTIONS + "/%s";
	public static final String PATH_COLLECTION_THNGS = PATH_COLLECTION + "/thngs";
	public static final String PATH_COLLECTION_THNG = PATH_COLLECTION_THNGS + "/%s";

	public CollectionService(ApiConfiguration config) {
		super(config);
	}

	/* ***** /collections ***** */

	/**
	 * POST {@value #PATH_COLLECTIONS}
	 * 
	 * @param collection
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Collection> collectionCreator(Collection collection) throws EvrythngClientException {
		return post(PATH_COLLECTIONS, collection, new TypeReference<Collection>() {
		});
	}

	/**
	 * GET {@value #PATH_COLLECTIONS}
	 * 
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Collection>> collectionsReader() throws EvrythngClientException {
		return get(PATH_COLLECTIONS, new TypeReference<List<Collection>>() {
		});
	}

	/* ***** /collections/{id} ***** */

	/**
	 * GET {@value #PATH_COLLECTION}
	 * 
	 * @param collectionId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Collection> collectionReader(String collectionId) throws EvrythngClientException {
		return get(String.format(PATH_COLLECTION, collectionId), new TypeReference<Collection>() {
		});
	}

	/**
	 * PUT {@value #PATH_COLLECTION}
	 * 
	 * @param collectionId
	 * @param collection
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Collection> collectionUpdater(String collectionId, Collection collection) throws EvrythngClientException {
		return put(String.format(PATH_COLLECTION, collectionId), collection, new TypeReference<Collection>() {
		});
	}

	/**
	 * DELETE {@value #PATH_COLLECTION}
	 * 
	 * @param collectionId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> collectionDeleter(String collectionId) throws EvrythngClientException {
		return delete(String.format(PATH_COLLECTION, collectionId));
	}

	/* ***** /collections/{id}/thngs ***** */

	/**
	 * GET {@value #PATH_COLLECTION_THNGS}
	 * 
	 * @param collectionId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Thng>> thngsReader(String collectionId) throws EvrythngClientException {
		return get(String.format(PATH_COLLECTION_THNGS, collectionId), new TypeReference<List<Thng>>() {
		});
	}

	/**
	 * PUT {@value #PATH_COLLECTION_THNGS}
	 * 
	 * @see #thngsAdder(String, List)
	 * @param collectionId
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<String>> thngAdder(String collectionId, String thngId) throws EvrythngClientException {
		return thngsAdder(collectionId, Arrays.asList(thngId));
	}

	/**
	 * PUT {@value #PATH_COLLECTION_THNGS}
	 * 
	 * @param collectionId
	 * @param thngs
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<String>> thngsAdder(String collectionId, List<String> thngs) throws EvrythngClientException {
		return put(String.format(PATH_COLLECTION_THNGS, collectionId), thngs, new TypeReference<List<String>>() {
		});
	}

	/* ***** /collections/{id}/thngs/{id} ***** */

	/**
	 * DELETE {@value #PATH_COLLECTION_THNG}
	 * 
	 * @param collectionId
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> thngRemover(String collectionId, String thngId) throws EvrythngClientException {
		return delete(String.format(PATH_COLLECTION_THNG, collectionId, thngId));
	}

	/**
	 * DELETE {@value #PATH_COLLECTION_THNGS}
	 * 
	 * @param collectionId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> thngsRemover(String collectionId) throws EvrythngClientException {
		return delete(String.format(PATH_COLLECTION_THNGS, collectionId));
	}
}
