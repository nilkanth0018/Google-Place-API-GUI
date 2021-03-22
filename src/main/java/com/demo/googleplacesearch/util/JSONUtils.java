package com.demo.googleplacesearch.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.demo.googleplacesearch.model.IModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JSONUtils {
	/**
	 * Convert the JSON of the Model using model fields.
	 * 
	 * @return JSON string.
	 * @throws JsonProcessingException
	 */
	public static String getJSON(IModel model) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer();
		String json = ow.writeValueAsString(model);
		return json;
	}

	public static JSONObject getJSONObject(String stringToParse) throws ParseException {
		JSONParser parser = new JSONParser();
		return (JSONObject) parser.parse(stringToParse);
	}
}
