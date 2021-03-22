package com.demo.googleplacesearch.gui.screens;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import com.demo.googleplacesearch.controller.CommonController;
import com.demo.googleplacesearch.controller.WelcomeController;
import com.demo.googleplacesearch.entity.SearchEntity;
import com.demo.googleplacesearch.gui.MainFrame;
import com.demo.googleplacesearch.util.Constants;
import com.demo.googleplacesearch.util.ImageLoader;
import com.demo.googleplacesearch.util.PropertyKeys;

import net.miginfocom.swing.MigLayout;

/**
 * Screen that get displayed at the Start of the application. Shows the button
 * to start the application process.
 *
 */
public class WelcomeScreen extends AbstractScreen {

	private JTextField txtSearchBar;

	private JButton btnSearch;
	
	private JLabel lblLoading;

	private JButton btnExport;

	private DefaultTableModel defaultTableModel;

	private JTable dataTable;

	private JScrollPane tabelScrollPane;

	private Object[][] data = {};

	private String[] columnNames = { "Place ID", "Business Name", "Business address", "City", "State", "Zip Code" };

	private Logger logger = Logger.getLogger(WelcomeScreen.class);

	private WelcomeController welcomeController;

	private String searchString;

	public WelcomeScreen(WelcomeController welcomeController, CommonController commonController) {
		super(commonController);
		this.welcomeController = welcomeController;
		initGUI();
	}

	protected void initGUI() {
		setLayout(new MigLayout("insets 5 5 5 5"));

		txtSearchBar = new JTextField();
		txtSearchBar.setFont(Constants.FONT_22_PLAIN);

		btnSearch = new JButton("Search");
		btnSearch.setFont(Constants.FONT_18_BOLD);
		btnSearch.setHorizontalTextPosition(JLabel.CENTER);
		btnSearch.addActionListener(startButtonActionListener);
		
		ImageIcon image = ImageLoader.loadImage(PropertyKeys.LOADING_IMAGE);
		lblLoading = new JLabel(image);
		lblLoading.setVisible(false);

		btnExport = new JButton("Export");
		btnExport.setFont(Constants.FONT_18_BOLD);
		btnExport.setHorizontalTextPosition(JLabel.CENTER);
		btnExport.addActionListener(exportButtonActionListener);
		btnExport.setEnabled(false);

		defaultTableModel = new DefaultTableModel(data, columnNames);
		dataTable = new JTable(defaultTableModel);
		tabelScrollPane = new JScrollPane(dataTable);

		TableColumnModel tcm = dataTable.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));

		add(txtSearchBar, "h 30!, pushx, growx");
		add(btnSearch, "wrap");
		add(lblLoading, "h 30!, w 30!, al c");
		add(btnExport, "al r, wrap");
		add(tabelScrollPane, "span 2, push, grow");

		dataTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				TableModel model =  dataTable.getModel();
				int row = dataTable.getSelectedRow();
				int col = dataTable.getSelectedColumn();
				if (col == 0) {
					URI uri;
					try {
						String place_id = (String)  model.getValueAt(row, 0);
						uri = new URI("https://www.google.com/maps/place/?q=place_id:" + place_id );
						open(uri);
					} catch (URISyntaxException e1) {
						logger.error("error ", e1);
					}
				}
			}
		});
		revalidate();
	}

	private ActionListener startButtonActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			searchString = txtSearchBar.getText();
			
			if(searchString.isEmpty()) {
				JOptionPane.showMessageDialog(WelcomeScreen.this, "Please input some text in search bar.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} 
			
			lblLoading.setVisible(true);
			btnSearch.setEnabled(false);
			btnExport.setEnabled(false);
			
			Thread search = new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					searchString = txtSearchBar.getText();
					if (searchString.length() > 0) {
						searchString = txtSearchBar.getText();
						try {
							defaultTableModel.setRowCount(0);
							List<SearchEntity> searches = welcomeController.performSearchOperation(searchString);
							String[] data = new String[6];
							for (SearchEntity search : searches) {
								data[0] = search.getPlaceId();
								data[1] = search.getName();
								data[2] = search.getBusinessAddress();
								data[3] = search.getCity();
								data[4] = search.getState();
								data[5] = search.getZipCode();
								defaultTableModel.addRow(data);
							}
							dataTable.validate();
						} catch (Exception e1) {
							logger.error("Error occured", e1);
							JOptionPane.showMessageDialog(WelcomeScreen.this, "Error occured while searching address", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {

					}
					
					lblLoading.setVisible(false);
					btnSearch.setEnabled(true);
					btnExport.setEnabled(true);
					revalidate();
				}
			});
			search.start();
			
		}
	};

	private ActionListener exportButtonActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			File file = getFile();
			if(file != null) {
				String pathToExportTo = file.getAbsolutePath();
				exportToCSV(dataTable, pathToExportTo);
				JOptionPane.showConfirmDialog(WelcomeScreen.this, "Data exported sucessfully!!!", "CSV Export", JOptionPane.DEFAULT_OPTION);
			}
		}
	};

	// Then elsewhere as from the McDowell answer
	private static void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				/* TODO: error handling */ }
		} else {
			/* TODO: error handling */ }
	}
	
	private static File getFile(){
	    JFileChooser fc = new JFileChooser();
	    File file = null;
	    int returnVal = fc.showOpenDialog(null);

	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	        file = fc.getSelectedFile();  
	    } 
	    return file;
	}
	
	public static boolean exportToCSV(JTable tableToExport,
	        String pathToExportTo) {

	    try {

	        TableModel model = tableToExport.getModel();
	        FileWriter csv = new FileWriter(new File(pathToExportTo));

	        for (int i = 1; i < model.getColumnCount(); i++) {
	            csv.write(model.getColumnName(i) + ",");
	        }

	        csv.write("\n");

	        for (int i = 0; i < model.getRowCount(); i++) {
	            for (int j = 1; j < model.getColumnCount(); j++) {
	            	String value = model.getValueAt(i, j) != null ? model.getValueAt(i, j).toString() : "";
	                csv.write("\"" + value + "\",");
	            }
	            csv.write("\n");
	        }

	        csv.close();
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
