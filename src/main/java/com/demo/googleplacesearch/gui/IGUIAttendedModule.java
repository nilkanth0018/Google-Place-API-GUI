package com.demo.googleplacesearch.gui;

/**
 * Interface {@link IGUIAttendedModule} used to provide API for the UI screens,
 * these API should be implemented by the GUI frame and get called to show the
 * different screens.
 *
 */
public interface IGUIAttendedModule {

	/**
	 * API to show the Welcome screen on UI.
	 */
	public void showWelcomeScreen();
}
