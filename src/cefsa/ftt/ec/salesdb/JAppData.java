package cefsa.ftt.ec.salesdb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JAppData {
	
	private final String COMMA = ",";
	private final String SEMICOLON = ";";
	
	private ArrayList<ClientEntity> client = new ArrayList<ClientEntity>();
	//private ArrayList<CompanyEntity> company;
	//private ArrayList<OccupationEntity> occupation;
	//private ArrayList<ProductEntity> product;
	//private ArrayList<SeleHistoryEntity> seleHistory;	
		
	public void loadClientData(String fileName) {
		
		String textLine;
		String fields[];
					
		try {
			
	        FileReader fstream = new FileReader(fileName);
	        BufferedReader fileIn = new BufferedReader(fstream);
	        textLine = fileIn.readLine();
	        
	        while ((textLine = fileIn.readLine()) != null)   {
	        	  
	        	  fields = textLine.split(SEMICOLON);
	        	          	  
	        	  //ID	Name	Index	DoB	Hire Date	 Credit 	Days	ID Company	ID Occupation
	        	  this.client.add(new ClientEntity(
	        			  					   fields[0],
	        			  				 	   fields[1],
	        			  				       fields[2],
	        			                       fields[3],
	        			                       fields[4],
	        			                       fields[5],
	        			                       fields[6],
	        			                       fields[7],
	        			                       fields[8]
	        			                       ));
	        	  
	        } //while	        
	        
	        fileIn.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} //try
			
	} //loadData
	
} //Data

