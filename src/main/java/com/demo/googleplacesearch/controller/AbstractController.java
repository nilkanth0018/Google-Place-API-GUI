package com.demo.googleplacesearch.controller;

import com.demo.googleplacesearch.gui.IGUIAttendedModule;

public abstract class AbstractController {

	protected IGUIAttendedModule guiAttendedModule;

	public AbstractController(IGUIAttendedModule aGUIAttendedModule) {
		guiAttendedModule = aGUIAttendedModule;
		initServices();
	}

	protected abstract void initServices();

}
