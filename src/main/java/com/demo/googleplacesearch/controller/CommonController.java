package com.demo.googleplacesearch.controller;

import org.apache.log4j.Logger;
import org.omg.IOP.TransactionService;

import com.demo.googleplacesearch.gui.IGUIAttendedModule;
import com.demo.googleplacesearch.service.impl.HTTPService;

/**
 * {@link CommonController} is a single controller is used in the application to
 * process common processes and navigation between UI and services. Used for
 * transaction process using {@link TransactionService}
 *
 */

public class CommonController extends AbstractController {

	private HTTPService httpService;

	private final Logger logger = Logger.getLogger(CommonController.class);

	public CommonController(IGUIAttendedModule aGUIAttendedModule) {
		super(aGUIAttendedModule);
	}


	@Override
	protected void initServices() {
		try {
			httpService = HTTPService.getInstance();
		} catch (Exception e) {
			logger.error("Error initializing HTTP Service", e);
		}
	}
}
