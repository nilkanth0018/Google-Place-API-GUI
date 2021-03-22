package com.demo.googleplacesearch.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.PropertyException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.demo.googleplacesearch.util.JSONUtils;
import com.demo.googleplacesearch.util.PropertyKeys;
import com.demo.googleplacesearch.util.PropertyResourceLoader;

/**
 * HTTP Service class that provides the API to send request to server.
 */
public final class HTTPService {

	private final Logger logger = Logger.getLogger(HTTPService.class);

	private static HTTPService INSTANCE;

	/**
	 * Instantiates a new HTTP service.
	 *
	 * @param secretKey Application Secret Key.
	 * @throws Exception the exception
	 */

	public static HTTPService getInstance() throws Exception {
		if (INSTANCE == null) {
			INSTANCE = new HTTPService();
		}
		return INSTANCE;

	}

	private HTTPService() {
		super();
	}

	/**
	 * Execute get Operation to HTTP Server.
	 *
	 * @param targetURL the target URL
	 * @return the http response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public HttpResponse executeGet(String targetURL) throws IOException {
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(targetURL);

			HttpResponse response = httpClient.execute(request);
			logger.info("REST Call for " + targetURL + " response code: " + response.getStatusLine().getStatusCode());
			return response;
		} catch (IOException exception) {
			logger.error("Error while sending GET Request ", exception);
			throw exception;
		}

	}

	/**
	 * Execute post HTTP Operation with encrypted secret key as a part of the
	 * payload body. Payload will be in the format of {"secretKey" : "", "data" :""}
	 * 
	 * @param targetURL  the target URL
	 * @param jsonString the json string
	 * @return the http response as {@link HttpResponse}
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
//	public HttpResponse executePostWithSecretKey(String targetURL, String jsonString) throws IOException {
//		try {
//			String encryptedJSON = SecurityUtils.encryptWithSecretKey(secretKey, jsonString);
//
//			String payload = "{\"secretKey\" : \"" + encryptedSecretKey + "\", \"data\" :\"" + encryptedJSON + "\"}";
//			StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
//			HttpClient httpClient = HttpClientBuilder.create().build();
//			HttpPost request = new HttpPost(targetURL);
//			request.setEntity(entity);
//
//			HttpResponse response = httpClient.execute(request);
//			logger.debug(
//					"POST Request for : " + targetURL + ". Response Code: " + response.getStatusLine().getStatusCode());
//			return response;
//		} catch (IOException exception) {
//			logger.error("Error while sending POST Request ", exception);
//			throw exception;
//		}
//
//	}

	private String convertInputStreamToString(InputStream inputStream) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		String resultString = result.toString(StandardCharsets.UTF_8.name());
		result.close();
		return resultString;
	}

	/**
	 * Gets the JSON object from {@link HttpResponse} entity.
	 * 
	 * @param response the response as {@link HttpResponse}
	 * @return the JSON object as {@link JSONObject}
	 * @throws Exception the exception
	 */
	public JSONObject getJSONObject(HttpResponse response) throws Exception {
		if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String responseText = convertInputStreamToString(response.getEntity().getContent());
			JSONObject object = JSONUtils.getJSONObject(responseText);
			return object;
		}
		return null;
	}

	/**
	 * Execute post HTTP operation with PostURL, Application ID and data need to
	 * post on server.
	 *
	 * @param postURL         the post URL
	 * @param standaloneAppId the standalone app id
	 * @param data            the data
	 * @return the http response as {@link HttpResponse}
	 * @throws Exception the exception
	 */
//	public HttpResponse executePost(String postURL, String standaloneAppId, String data) throws Exception {
//		try {
//			String appId = SecurityUtils.encryptWithPublicKey(publicKey, standaloneAppId);
//			String payload = Constants.BLANK;
//			if (data != null) {
//				String encryptedJSON = SecurityUtils.encryptWithSecretKey(secretKey, data);
//				payload = "{\"standaloneAppId\" : \"" + appId + "\", \"data\" :\"" + encryptedJSON + "\"}";
//			} else {
//				payload = "{\"standaloneAppId\" : \"" + appId + "\"}";
//			}
//
//			StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
//
//			HttpClient httpClient = HttpClientBuilder.create().build();
//			HttpPost request = new HttpPost(postURL);
//			request.setEntity(entity);
//
//			HttpResponse response = httpClient.execute(request);
//			logger.debug(
//					"POST Request for : " + postURL + ". Response Code: " + response.getStatusLine().getStatusCode());
//			return response;
//		} catch (IOException exception) {
//			logger.error("Error while sending POST Request ", exception);
//			throw exception;
//		}
//	}
}
