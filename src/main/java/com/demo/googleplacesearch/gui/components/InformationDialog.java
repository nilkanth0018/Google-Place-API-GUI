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
 * Information Dialog Box to show the Custom Information Dialog
 * <p>
 * Error dialog with border and Information icon provides constructor to pass
 * Information message. OK button will hide the Information dialog.
 * </p>
 *
 */
public class InformationDialog extends JPanel implements ActionListener {

	private JLabel lblTitle;

	private JLabel txtInformation;

	private JLabel imgInformation;

	private JButton btnOK;

	private ActionListener listener;

	public InformationDialog(String infoText, ActionListener listener) {
		super();
		this.listener = listener;
		lblTitle = new JLabel("Information");
		txtInformation = getInfoTextLabel(infoText);
		btnOK = new JButton("OK");
		initUI();
	}

	private JLabel getInfoTextLabel(String infoText) {
		JLabel label = new JLabel();
		label.setText("<html><p>" + infoText + "</p></html>");
		label.setFont(Constants.FONT_22_PLAIN);
		return label;
	}

	private void initUI() {
		setLayout(new MigLayout("insets 5 5 5 5", "2%[]2%", "2%[10%][60%][15%]2%"));
		setBackground(Color.WHITE);
		setBorder(Constants.PANEL_BORDER);

		lblTitle.setFont(Constants.FONT_24_BOLD);
		imgInformation = new JLabel(ImageLoader.loadImage(PropertyKeys.SUCCESS_IMAGE));
		btnOK.addActionListener(this);
		if (this.listener != null) {
			btnOK.addActionListener(this.listener);
		}

		add(lblTitle, "alignx center, cell 0 0");
		add(imgInformation, "alignx left,aligny center, width 20%, cell 0 1");
		add(txtInformation, "alignx left, aligny center, gapleft 2%, width 80%, cell 0 1");
		add(btnOK, "width 30%, height 15%, alignx center,aligny bottom, push, cell 0 2");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().hideGlassPanel();
	}

}
