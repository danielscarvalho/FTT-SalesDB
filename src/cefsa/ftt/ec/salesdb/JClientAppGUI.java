package cefsa.ftt.ec.salesdb;

import java.awt.*; 
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.io.File;

public class JClientAppGUI {
	
	private JFrame frame;
	private JLoginDialog loginDialog;
	private JMenuBar menuBar;
	private JMenu menu1,menu2,menu3;
	private JMenuItem mi11, mi21, mi22, mi23, mi24, mi25, mi31;		//menu item
	private JDBConnect dbConnect;
	private JFileChooser fileChooser;
	private JTabbedPane mainTab;	

	private JPanel panelClient;
	private JPanel panelCompany;
	private JPanel panelProduct;
	private JPanel panelSale;
	private JPanel panelOccupation; 
	private JPanel panelButtonDbBar;
	private JPanel panelProdFields;
	
	//Product tab stuff
	private JButton buttonProdRefresh, 
	                buttonProdUpdate, 
	                buttonProdNew, 
	                buttonProdDelete;
	private JTable productGrid;
	private JScrollPane scrollProduct;
	private JScrollPane scrollProductFields;
	private JLabel labelProdId,
	               labelProdPartNumber,
	               labelProdDesc,
	               labelProdSize,
	               labelProdStatus,
	               labelProdValue; 
	
	private JTextField fieldProdId,
    				   fieldProdPartNumber,
    				   fieldProdDesc,
    				   fieldProdSize,
    				   fieldProdStatus,
    				   fieldProdValue;	
	

	JClientAppGUI() {
		
		dbConnect = new JDBConnect();
		
		do {			
		
			loginDialog = new JLoginDialog(dbConnect);

			try {
				
				dbConnect.openConnection();
			
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
			} //try
			
		} while(dbConnect.getConnection()==null);
		
		frame = new JFrame("JClientApp");
		
		panelClient = new JPanel(new BorderLayout());
		panelCompany = new JPanel(new BorderLayout());
		panelProduct = new JPanel(new BorderLayout());
		panelSale = new JPanel(new BorderLayout());
		panelOccupation = new JPanel(new BorderLayout());
		panelButtonDbBar = new JPanel(new FlowLayout());
		
		panelProdFields = new JPanel(new SpringLayout());
						
		buttonProdNew = new JButton("Insert/New");
		buttonProdUpdate = new JButton("Update");
		buttonProdDelete = new JButton("Delete");
		buttonProdRefresh = new JButton("Refresh Data");
		
		labelProdId = new JLabel("ID");
		labelProdPartNumber = new JLabel("Part Number");
		labelProdDesc = new JLabel("Description");
		labelProdSize = new JLabel("Size");
		labelProdStatus = new JLabel("Status");
		labelProdValue = new JLabel("Value");
		
		fieldProdId = new JTextField();
		fieldProdPartNumber = new JTextField();
		fieldProdDesc = new JTextField();
		fieldProdSize = new JTextField();
		fieldProdStatus = new JTextField();
		fieldProdValue = new JTextField();		
		
		productGrid = new JTable(100, 6);
		productGrid.setRowSelectionAllowed(true);
		//productGrid.setEnabled(false);

		scrollProductFields = new JScrollPane();
		scrollProductFields.setViewportView(panelProdFields);		
		
		scrollProduct = new JScrollPane();
		scrollProduct.setViewportView(productGrid);

		
		mainTab = new JTabbedPane();
					
		menuBar = new JMenuBar();		
		
		menu1 = new JMenu("File");
		menu2 = new JMenu("Data");
		menu3 = new JMenu("Help");
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		
		mi11 = new JMenuItem("Quit"); 
		mi21 = new JMenuItem("Import client");
		mi22 = new JMenuItem("Import occupation");
		mi23 = new JMenuItem("Import product");
		mi24 = new JMenuItem("Import selling");
		mi25 = new JMenuItem("Import company");
		mi31 = new JMenuItem("About");
			
		menu1.add(mi11);
		menu2.add(mi21);
		menu2.add(mi22);
		menu2.add(mi23);
		menu2.add(mi24);
		menu2.add(mi25);
		menu3.add(mi31);
		
		frame.setJMenuBar(menuBar);	
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	} //JClientAppGUI

	public void launchFrame() {
		
		frame.add(mainTab, BorderLayout.CENTER);		
		
		panelProduct.add(panelButtonDbBar , BorderLayout.NORTH);
		
		panelButtonDbBar.add(buttonProdRefresh);		
		panelButtonDbBar.add(buttonProdNew);
		panelButtonDbBar.add(buttonProdUpdate);
		panelButtonDbBar.add(buttonProdDelete);
		
		panelProduct.add(scrollProductFields, BorderLayout.CENTER);

		panelProdFields.add(labelProdId);
		labelProdId.setLabelFor(fieldProdId);
		panelProdFields.add(fieldProdId);
		
		
		panelProdFields.add(labelProdPartNumber);
		labelProdPartNumber.setLabelFor(fieldProdPartNumber);
		panelProdFields.add(fieldProdPartNumber);
		
		
		panelProdFields.add(labelProdDesc);
		labelProdDesc.setLabelFor(fieldProdDesc);
		panelProdFields.add(fieldProdDesc);
		
		
		panelProdFields.add(labelProdSize);
		labelProdSize.setLabelFor(fieldProdSize);
		panelProdFields.add(fieldProdSize);
		
		
		panelProdFields.add(labelProdStatus);
		labelProdStatus.setLabelFor(fieldProdStatus);
		panelProdFields.add(fieldProdStatus);
		
		
		panelProdFields.add(labelProdValue);
		labelProdValue.setLabelFor(fieldProdValue);
		panelProdFields.add(fieldProdValue);	
			
		SpringUtilities.makeCompactGrid(panelProdFields,
                6, 2,  //rows, cols
                6, 6,  //initX, initY
                6, 6); //xPad, yPad		
		
		panelProduct.add(scrollProduct, BorderLayout.SOUTH);
		//panelProduct.add(productGrid, BorderLayout.CENTER);

			
		mainTab.add("Client", panelClient);
		mainTab.add("Company", panelCompany);
		mainTab.add("Product", panelProduct);
		mainTab.add("Sale", panelSale);
		mainTab.add("Occupation", panelOccupation);		
		
		frame.setSize(800,600);
	    frame.setLocation(10, 10);
	    
	    frame.pack();
		frame.setVisible(true);
		
//// Action Listeners - Anonymous Classes
	
		
		buttonProdRefresh.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent ae) {
                //Execute when button is pressed
                System.out.println("You clicked the button Prod Refresh");



                String[] columnNames = {"ID",
                		"Part Number",
                		"Description",
                		"Size",
                		"Status",
                		"Value"};
                
              //Test
                
             
                productGrid = new JTable(dbConnect.getProductData(), columnNames);
                /*
                Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3"},
                        			   { "Row2-Column1", "Row2-Column2", "Row2-Column3"} };
                        			   
                Object columnNames2[] = { "Column One", "Column Two", "Column Three"};
               
                productGrid = new JTable(rowData, columnNames2);
                */
                scrollProduct = new JScrollPane(productGrid);
                panelProduct.add(scrollProduct, BorderLayout.SOUTH);
                frame.pack();
            
                
                
                
            }//actionPerformed
        }); 	//addActionListener	

		
	
		buttonProdDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {	
			
				int row = productGrid.getSelectedRow();
				int column = productGrid.getColumnCount();
				
				for(int i = 0; i < column; i++) {
				    System.out.print(productGrid.getValueAt(row, i) + " - ");
				}//for
				
				System.out.println();
				
				dbConnect.deleteProductData(productGrid.getValueAt(row, 1));
			
			}
			
		});
		
		// Menu Exit 
		mi11.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent ae) {
            	
            	closeDbAndExit();
            	
            } //actionPerformed
        }); 	//addActionListener		
		
		// Menu About 
		mi31.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent ae) {
            	
            	JOptionPane.showMessageDialog(frame,            			
        				"Author: Daniel de Souza Carvalho\n" +
        				"@danielscarvalho\n" +
        				"CEFSA - FTT\n"+
        				"Engenharia da Computação\n"+
        				"Técnicas de Programação II - Java\n",
        				"About",
        				JOptionPane.OK_OPTION);
            	
            } //actionPerformed
        }); 	//addActionListener

		// Menu Import Product 
		mi23.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent ae) {
            	
            	fileChooser = new JFileChooser();            	            	
            	
            	FileFilter filter = new FileNameExtensionFilter("CSV files", "csv");
                fileChooser.addChoosableFileFilter(filter);

                int ret = fileChooser.showDialog(null, "Open file");

                if (ret == JFileChooser.APPROVE_OPTION) {
                  File file = fileChooser.getSelectedFile();
                  
                  System.out.println(file);
                  
                  dbConnect.loadProduct(file);
                  
                } //IF
            	
            	
            } //actionPerformedh
        }); 	//addActionListener
		
		// Window Listener Close
	    frame.addWindowListener(new WindowAdapter() {
	    	
	    	public void windowClosing(WindowEvent e) {
	    			    		
	    			closeDbAndExit();
	    		
	    	}//windowClosing
	    });	//addWindowListener
	
	} //launchFrame			
	
	private void closeDbAndExit() {
		
		int exitApp = JOptionPane.showConfirmDialog(frame,
				"Do you really wish to exit the application",
				"Please Confirm", 
				JOptionPane.YES_NO_OPTION);
		
		if (exitApp==JOptionPane.YES_OPTION) {
			
			try {
				dbConnect.closeConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			} //try
			
			System.exit(0);
			
		} //IF		    	
    	
	} //CloseDbAndExit
	
} //JClientAppGUI

