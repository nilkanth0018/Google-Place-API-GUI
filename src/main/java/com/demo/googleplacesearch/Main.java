package com.demo.googleplacesearch;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.apache.log4j.PropertyConfigurator;

import com.demo.googleplacesearch.gui.MainFrame;
import com.demo.googleplacesearch.gui.IGUIAttendedModule;

/**
 * Hello world!
 *
 */
public class Main {
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		PropertyConfigurator.configure("config/log.properties");
		SwingUtilities.invokeAndWait(() -> {
			IGUIAttendedModule guiModule = MainFrame.getInstance();
			try {
				guiModule.showWelcomeScreen();
				MainFrame.getInstance().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
