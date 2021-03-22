package com.demo.googleplacesearch.util;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import com.demo.googleplacesearch.gui.screens.WelcomeScreen;

/**
 * Image loader class loads the image based on the provided {@link PropertyKeys}
 * {@link PropertyKeys} should be defined to get the image.
 * 
 */
public class ImageLoader {
	private static Logger logger = Logger.getLogger(WelcomeScreen.class);

	public static ImageIcon loadImage(PropertyKeys propertyKey) {
		try {
			return new ImageIcon(PropertyResourceLoader.getInstance().getProperty(propertyKey));
		} catch (Exception exception) {
			logger.error("Error occured while loading Image", exception);
		}
		return null;
	}

	public static ImageIcon getScaledDimension(ImageIcon image, double percentage) {
		double height = image.getIconHeight() * percentage / 100.0;
		double ratio = (double) (image.getIconWidth()) / image.getIconHeight();
		image.setImage(image.getImage().getScaledInstance((int) (height * ratio), (int) height, Image.SCALE_SMOOTH));
		return image;
	}

}
