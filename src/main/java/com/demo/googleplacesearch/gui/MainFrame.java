package com.demo.googleplacesearch.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.demo.googleplacesearch.controller.CommonController;
import com.demo.googleplacesearch.controller.WelcomeController;
import com.demo.googleplacesearch.gui.components.ErrorDialog;
import com.demo.googleplacesearch.gui.components.InformationDialog;
import com.demo.googleplacesearch.gui.screens.AbstractScreen;
import com.demo.googleplacesearch.gui.screens.WelcomeScreen;
import com.demo.googleplacesearch.util.Constants;
import com.demo.googleplacesearch.util.PropertyKeys;
import com.demo.googleplacesearch.util.PropertyResourceLoader;

import net.miginfocom.swing.MigLayout;

/**
 * The Class MainFrame. Main Application Frame shows all the panels and UI.
 * 
 */
public final class MainFrame extends JFrame implements IGUIAttendedModule {
	/**
	 * singleton instance.<br/>
	 */
	private static MainFrame ourInstance = new MainFrame();

	private CommonController commonController;

	private AbstractScreen currentScreen;

	private JPanel glassPanel;

	private final Logger logger = Logger.getLogger(MainFrame.class);

	private int width;

	private int height;

	private JLabel lblLoading;

	public static synchronized MainFrame getInstance() {
		return ourInstance;
	}

	private MainFrame() {
		super();
		initCommonController();
		initUI();
	}

	/**
	 * Initialize the JFrame and it's components
	 */
	private void initUI() {
		width = Integer
				.parseInt(PropertyResourceLoader.getInstance().getProperty(PropertyKeys.APPLICATION_WIDTH, "1280"));
		height = Integer
				.parseInt(PropertyResourceLoader.getInstance().getProperty(PropertyKeys.APPLICATION_HEIGHT, "720"));
		setLayout(new MigLayout("insets 0 0 0 0", "[]", "[]"));
		setSize(new Dimension(width, height));

		lblLoading = new JLabel("Loading....");
		lblLoading.setBorder(Constants.GRAY_BORDER);

		setTitle("Google Place Search");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void initCommonController() {
		commonController = new CommonController(this);
	}

	@Override
	public void showWelcomeScreen() {
		logger.debug("Showing Welcome Screen");
		this.remove(currentScreen);
		WelcomeController welcomeController = new WelcomeController(this);
		WelcomeScreen welcomeScreen = new WelcomeScreen(welcomeController, commonController);
		currentScreen = welcomeScreen;
		add(welcomeScreen, "cell 0 1, width 100%, height 100%");
		revalidate();
	}

	public void showLoadingDialog() {
		logger.debug("Showing Loading panel with GlassPane.");
		glassPanel.removeAll();
		glassPanel.add(lblLoading, "alignx center, aligny center");
		getGlassPane().setVisible(true);
	}

	/**
	 * Initialize glass panel to show grey area while application is in process or
	 * loading.
	 */
	private void initializeGlassPanel() {

		JPanel glass = new JPanel();
		glass.setLayout(new MigLayout("align 50% 50%"));

		glassPanel = new JPanel(new MigLayout("align 50% 50%"));
		glassPanel.setMinimumSize(new Dimension(width, height));
		glassPanel.setBackground(Constants.GLASS_PANE_COLOR);

		glass.add(glassPanel, "alignx center, aligny center");
		glass.setOpaque(false);
		glass.addMouseListener(getDisabledMouseListener());
		setGlassPane(glass);
		getGlassPane().setVisible(false);
	}

	/**
	 * Show kiosk error message as a pop-up dialog box to the end-user.
	 * 
	 * @param errorDialog the error dialog as custom Error Dialog
	 *                    {@link ErrorDialog}
	 */
	public void showErrorMessage(ErrorDialog errorDialog) {
		logger.debug("Showing Error panel with GlassPane.");
		glassPanel.removeAll();
		glassPanel.add(errorDialog, "width 60%, height 25%, alignx center, aligny center");
		getGlassPane().setVisible(true);
	}

	/**
	 * Show kiosk Information message as a pop-up dialog box to the end-user.
	 * 
	 * @param infoDialog the Information dialog as custom Error Dialog
	 *                   {@link InformationDialog}
	 */
	public void showKioskInformationMessage(InformationDialog infoDialog) {
		logger.debug("Showing Info panel with GlassPane.");
		glassPanel.removeAll();
		glassPanel.add(infoDialog, "width 60%, height 25%, alignx center, aligny center");
		getGlassPane().setVisible(true);
	}

	/**
	 * Hide glass panel.
	 */
	public void hideGlassPanel() {
		glassPanel.removeAll();
		getGlassPane().setVisible(false);
	}

	/**
	 * Gets the disabled mouse listener.
	 * 
	 * @return the disabled mouse listener
	 */
	private MouseListener getDisabledMouseListener() {
		return new MouseListener() {

			@Override
			public void mouseReleased(final MouseEvent pEvent) {

				// empty method body for doing nothing on mouseevents.
			}

			@Override
			public void mousePressed(final MouseEvent pEvent) {

				// empty method body for doing nothing on mouseevents.
			}

			@Override
			public void mouseExited(final MouseEvent pEvent) {

				// empty method body for doing nothing on mouseevents.
			}

			@Override
			public void mouseEntered(final MouseEvent pEvent) {

				// empty method body for doing nothing on mouseevents.
			}

			@Override
			public void mouseClicked(final MouseEvent pEvent) {

				// empty method body for doing nothing on mouseevents.
			}
		};
	}

	@Override
	public void remove(Component comp) {
		if (comp != null) {
			super.remove(comp);
			getGlassPane().setVisible(false);
		}
	}

	@Override
	public void revalidate() {
		super.revalidate();
		if (currentScreen != null) {
			currentScreen.setComponentFocus();
		}
	}
}
