package com.demo.googleplacesearch.gui.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.demo.googleplacesearch.gui.MainFrame;
import com.demo.googleplacesearch.util.Constants;
import com.demo.googleplacesearch.util.ImageLoader;
import com.demo.googleplacesearch.util.PropertyKeys;

import net.miginfocom.swing.MigLayout;

/**
 * Error Dialog Box to show the Custom Error Dialog
 * <p>
 * Error dialog with border and Error icon provides
 * constructor to pass Error message.
 * OK button will hide the Error dialog.
 * </p>	
 *
 */
public class ErrorDialog extends JPanel implements ActionListener {

	private JLabel lblTitle;

	private JLabel txtError;

	private JLabel imgError;

	private JButton btnOK;

	public ErrorDialog(String errorText) {
		super();
		lblTitle = new JLabel("Error");
		txtError = getErrorTextLabel(errorText);
		btnOK = new JButton("OK");
		initUI();
	}

	private JLabel getErrorTextLabel(String errorText) {
		JLabel label = new JLabel();
		label.setText("<html><p>" + errorText + "</p></html>");
		label.setFont(Constants.FONT_22_PLAIN);
		return label;
	}

	private void initUI() {
		setLayout(new MigLayout("insets 5 5 5 5", "2%[]2%", "2%[10%][60%][15%]2%"));
		setBackground(Color.WHITE);
		setBorder(Constants.PANEL_BORDER);

		lblTitle.setFont(Constants.FONT_24_BOLD);
		imgError = new JLabel(ImageLoader.loadImage(PropertyKeys.ERROR_IMAGE));
		btnOK.addActionListener(this);

		add(lblTitle, "alignx center, cell 0 0");
		add(imgError, "alignx left,aligny center, width 20%, cell 0 1");
		add(txtError, "alignx left, aligny center, gapleft 2%, width 80%, cell 0 1");
		add(btnOK, "width 30%, height 15%, alignx center,aligny bottom, push, cell 0 2");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().hideGlassPanel();
	}

}
