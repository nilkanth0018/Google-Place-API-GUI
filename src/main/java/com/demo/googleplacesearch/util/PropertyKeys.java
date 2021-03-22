package com.demo.googleplacesearch.util;

/**
 * Enumeration to maintain the keys present in property files.
 *
 */
public enum PropertyKeys {
	APPLICATION_WIDTH("application.gui.width"),
	APPLICATION_HEIGHT("application.gui.height"),
	ERROR_IMAGE("application.gui.error_image"),
	SUCCESS_IMAGE("application.gui.success_image"),
	LOADING_IMAGE("application.gui.loading_image"),
	REST_URL_GET_TEXT_SEARCH("application.http.google_place_text_search"),
	GOOGLE_API_KEY("google.api.key");
	
	private String key;

	private PropertyKeys(String aKey) {
		key = aKey;
	}

	public String getKey() {
		return key.trim();
	}
}
