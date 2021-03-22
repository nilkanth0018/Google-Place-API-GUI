package com.demo.googleplacesearch.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.xml.bind.PropertyException;

import org.apache.log4j.Logger;

/**
 * The Class PropertyResourceLoader.
 * <p>
 * Loads the property file present in config folder with the name
 * application.properties.
 * </p>
 * Singleton class provides the method to access properties.
 */
public final class PropertyResourceLoader {
	private static Logger logger = Logger.getLogger(PropertyResourceLoader.class);

	private transient Properties properties = null;

	private static final String PROPERTY_FILENAME = "config/application.properties";

	private static PropertyResourceLoader instance = new PropertyResourceLoader();

	public static PropertyResourceLoader getInstance() {
		return instance;
	}

	/**
	 * Loads properties from application.properties file.
	 * 
	 * @throws Exception
	 */
	public void load() throws IOException {
		BufferedInputStream inputStream = null;
		try {

			logger.info("initializing properties from file [" + PROPERTY_FILENAME + "] ");
			properties = new Properties();
			try {
				inputStream = new BufferedInputStream(new FileInputStream(PROPERTY_FILENAME));
				properties.load(inputStream);
			} catch (IOException ex) {
				logger.info("IOException while loading application.properties file." + ex.getMessage());
				ex.printStackTrace();
			}
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.info("IOException while closing inputstream." + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * returns property for provided key.
	 * 
	 * @param pPropertyKey property key as {@link PropertyKeys}
	 * @return requested property as String
	 * @throws Exception
	 */
	public String getProperty(final PropertyKeys pPropertyKey) throws PropertyException, IOException {
		return loadProperty(pPropertyKey.getKey());
	}

	private String loadProperty(String pPropertyKey) throws PropertyException, IOException {
		if (pPropertyKey == null) {
			throw new IllegalArgumentException("provided key is null.");
		}
		if (properties == null) {
			load();
		}
		final String property = properties.getProperty(pPropertyKey);
		if (property == null) {
			throw new PropertyException("cannot locate property [" + pPropertyKey + "]");
		}
		return property;
	}

	/**
	 * tries to retrieve a property value for a specific key. If the key is not
	 * found in the properties file, it will return the provided default value.
	 * 
	 * @param pPropertyKey  property key as {@link PropertyKeys}
	 * @param pDefaultValue default value
	 * @return property value for a specific key
	 */
	public String getProperty(final PropertyKeys pPropertyKey, final String pDefaultValue) {
		String value = null;
		try {
			value = this.getProperty(pPropertyKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == value ? pDefaultValue : value;
	}

	/**
	 * Gets the boolean property.
	 * 
	 * @param pPropertyKey the property key as {@link PropertyKeys}
	 * @return the boolean property
	 * @throws Exception the exception
	 */
	public boolean getBooleanProperty(final PropertyKeys pPropertyKey) throws Exception {

		if (pPropertyKey == null) {
			throw new IllegalArgumentException("provided key is null.");
		}
		if (properties == null) {
			load();
		}
		final String property = properties.getProperty(pPropertyKey.getKey());
		if (property == null) {
			throw new Exception("cannot locate property [" + pPropertyKey + "]");
		}
		return Boolean.valueOf(property);
	}
	
	/**
	 * Gets the int property.
	 * 
	 * @param pPropertyKey the property key as {@link PropertyKeys}
	 * @return the int property
	 * @throws Exception the exception
	 */
	public int getIntegerProperty(final PropertyKeys pPropertyKey) throws Exception {

		String property = getProperty(pPropertyKey);
		return Integer.valueOf(property);
	}

	/**
	 * Gets the all keys.
	 * 
	 * @return the all keys in {@link Set}
	 */
	public Set<Object> getAllKeys() {
		Set<Object> keys = properties.keySet();
		return keys;
	}

	/**
	 * Gets the all properties.
	 * 
	 * @return the all properties in {@link HashMap}
	 * @throws Exception the exception
	 */
	public HashMap<String, String> getAllProperties() throws Exception {
		HashMap<String, String> properties = new HashMap<String, String>();
		for (Object k : getAllKeys()) {
			String key = (String) k;
			properties.put(key, loadProperty(key));
		}
		return properties;
	}

}
