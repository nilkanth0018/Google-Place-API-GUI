package com.demo.googleplacesearch.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.demo.googleplacesearch.entity.SearchEntity;
import com.demo.googleplacesearch.gui.IGUIAttendedModule;
import com.demo.googleplacesearch.service.impl.HTTPService;
import com.demo.googleplacesearch.util.PropertyKeys;
import com.demo.googleplacesearch.util.PropertyResourceLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WelcomeController extends AbstractController {

	private Logger logger = Logger.getLogger(WelcomeController.class);
	private HTTPService httpService;

	public WelcomeController(IGUIAttendedModule aGUIAttendedModule) {
		super(aGUIAttendedModule);
	}

	@Override
	protected void initServices() {
		try {
			httpService = HTTPService.getInstance();
		} catch (Exception e) {
			logger.error("HTTP Service not initialised", e);
		}
	}

	public List<SearchEntity> performSearchOperation(String searchString) throws Exception {
		if (httpService != null) {
			
			List<SearchEntity> searches = new ArrayList<SearchEntity>();
			String nextPageToken = null;
			do {
				String targetURL = PropertyResourceLoader.getInstance().getProperty(PropertyKeys.REST_URL_GET_TEXT_SEARCH);

				String apiKey = PropertyResourceLoader.getInstance().getProperty(PropertyKeys.GOOGLE_API_KEY);

				String finalURL;
				if (nextPageToken == null) {
					finalURL = targetURL + "?query=" + searchString.replace(" ", "+") + "&key=" + apiKey;
				} else {
					finalURL = targetURL + "?query=" + searchString.replace(" ", "+") + "&key=" + apiKey
							+ "&pagetoken=" + nextPageToken;
				}
				
				HttpResponse response = httpService.executeGet(finalURL);
				JSONObject responseObject = httpService.getJSONObject(response);
				logger.debug("Response recieved : " + responseObject);
				
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String message = responseObject.get("results").toString();
					ObjectMapper mapper = new ObjectMapper();
					List<SearchEntity> searchEntities = mapper.readValue(message, new TypeReference<List<SearchEntity>>() {
					});
					searches.addAll(searchEntities);					
				}
				
				nextPageToken = (String) responseObject.get("next_page_token");
				Thread.sleep(2000);
			} while (nextPageToken != null);
			
			return searches;
		} else {
			throw new Exception("HTTP Service is not initialised");
		}
	}
}
