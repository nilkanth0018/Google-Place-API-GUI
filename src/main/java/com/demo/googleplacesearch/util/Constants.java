package com.demo.googleplacesearch.util;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Common constants that get used in all application classes.
 *
 */
public class Constants {

	public static final Font FONT_14_BOLD = new Font("Calibri", Font.BOLD, 14);
	public static final Font FONT_14_PLAIN = new Font("Calibri", Font.PLAIN, 14);
	public static final Font FONT_18_BOLD = new Font("Calibri", Font.BOLD, 18);

	public static final Font FONT_24_BOLD = new Font("Calibri", Font.BOLD, 24);
	public static final Font FONT_22_PLAIN = new Font("Calibri", Font.PLAIN, 22);
	public static final Font FONT_22_BOLD = new Font("Calibri", Font.BOLD, 22);
	
	public static final Font FONT_28_BOLD = new Font("Calibri", Font.BOLD, 28);

	public static final Color HEADER_BACKGROUND = new Color(0, 162, 232);

	public static final Color BLUE_BACKGROUND = new Color(91, 155, 213);

	public static final int ONE_SECOND = 1000;

	public static final Border PANEL_BORDER = BorderFactory.createLineBorder(new Color(54, 162, 214), 3);

	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm a");

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
	
	public static final String DB_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String BLANK = "";

	public static final Border GRAY_BORDER = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);
	public static final Border BLUE_BORDER = BorderFactory.createLineBorder(new Color(54, 162, 214), 3);
	
	public static final Color GLASS_PANE_COLOR = new Color(122, 122, 122, 50);
	
	public static final long WEB_SOCKET_CONNECT_TIME_OUT = 120000;
}
