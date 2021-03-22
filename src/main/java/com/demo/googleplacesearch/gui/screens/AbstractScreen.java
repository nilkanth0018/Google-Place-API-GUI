package com.demo.googleplacesearch.gui.screens;

import java.awt.Color;

import javax.swing.JPanel;

import com.demo.googleplacesearch.controller.CommonController;


/**
 * Abstract class for the applications UI screens, Here common fields or common
 * method along with API methods get defined. <b> All the UI screen must extend
 * this AbstractScreen class </b>
 */
public abstract class AbstractScreen extends JPanel {

	protected CommonController commonController;

	public AbstractScreen(CommonController commonController) {
		this.commonController = commonController;
		setBackground(Color.WHITE);
	}

	public void setComponentFocus() {

	}
}
