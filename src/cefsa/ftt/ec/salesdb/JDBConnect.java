package cefsa.ftt.ec.salesdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Vector;

class JDBConnect {
	
	private String dbUser, 
			       dbPassword, 			       
			       dbConnectionString,
			       dbDriver;
	
	private Connection dbCon;
	private Statement dbStmt;
	private ResultSet dbRs;

	//TODO: Create methods for different DBs
	//TODO: Add MS SQL Server too
	//TODO: Add at UI a DB selection option at login... perhaps...
	
	
    JDBConnect() {
		
    	setMySQLDb();
		
		setDbUser("salesdb");
		
	} //Constructor
	
	private void setOracleDb() {
	    //default driver is Oracle JDBC
		//Oracle Connection
		//Driver: http://www.oracle.com/technetwork/apps-tech/jdbc-112010-090769.html
		setDbDriver("oracle.jdbc.OracleDriver");
		//default DB connection is a local Oracle Express Edition
		setDbConString("jdbc:oracle:thin:@//127.0.0.1:1521/xe");
	}
	
	private void setMsSqlServerDb( ) {
		//https://docs.microsoft.com/pt-br/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server
		setDbDriver("");
		
		setDbConString("jdbc:sqlserver://localhost:1433;");
	}
		
	private void setMySQLDb() {
		//Driver https://dev.mysql.com/downloads/connector/j/
		setDbDriver("com.mysql.jdbc.Driver");
		setDbConString("jdbc:mysql://localhost:3306/salesdb");
	}
	
	public Connection getConnection() {
		return dbCon;
	} //SQLException
	
	public void closeConnection() throws Exception {
		
		if (dbRs!=null)
			dbRs.close();
		
		if (dbStmt!=null)
			dbStmt.close();
		
		if (dbCon!=null)
			dbCon.close();
		
	} //closeConnection
	
	public ResultSet getResultSet(String query) throws SQLException {
		
		return dbStmt.executeQuery(query);
		
	} //getResult
	
	public void openConnection() throws Exception {

		// Load the Driver class.
	   Class.forName(getDbDriver());
	   //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	   
		// If you are using any other database then load the right driver here.

	   //Create the connection using the static getConnection method
	   //NUNCA SALVE SENHA E USUÁRIO DO BD DENTRO DO CÓDIGO JAVA!!!

	   dbCon = DriverManager.getConnection (getDbConString(),
                                            getDbUser(),
                                            getDbPassword());
       
	   //dbCon.getMetaData();
	    
	   //Create a Statement class to execute the SQL statement
	   dbStmt = dbCon.createStatement();	        
		
	} //doConnect()
	
	public String getDbDriver() {
		return dbDriver;
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbConString() {
		return dbConnectionString;
	}

	public void setDbConString(String dbConnection) {
		this.dbConnectionString = dbConnection;
	}
	
	public void loadProduct(File file) {
		
		String fields[] = {null,null,null,null,null};
		String sql = null;
		String line = null;
		
		try {

		      BufferedReader input =  new BufferedReader(new FileReader(file));
		      
		      try {
		    	  
		        line = null; 
		        
		        line = input.readLine(); //First line header, jump
		        
		        while (( line = input.readLine()) != null) {		        	
		        
		          fields = line.split(";");		          	          
		          
			      sql = "INSERT INTO salesdb.product VALUES (" +
		  					fields[0] + "," +
		  					"\'" + fields[1] + "\'," +
		  					"\'" + fields[2] + "\'," +
		  					"\'" + fields[3] + "\'," +
		  					"\'" + fields[4] + "\'," +
		  					fields[5] + ")";			      			    
			      
			      System.out.println(sql);
			      
		          dbStmt.executeUpdate(sql);
		          
		        } //while 		        
		        
		        dbStmt.executeBatch();
		        
		      } finally {
		        input.close();
		      } //finally
		      
		    } catch (Exception ex) {
		      ex.printStackTrace();
		      
		      System.out.println();
		      System.out.println(line);
		      System.out.println(fields[0] + " " + fields[5]);	
		      System.out.println(sql);
		      
		    } //try
		
	} //loadProduct
	
	public Object[][] getProductData() {
		
		Object outData[][]= new Object[1][1];
		int columnCount;
		int recordCount=0;
		
		try {
			
			dbRs = dbStmt.executeQuery("SELECT COUNT(1) FROM salesdb.product");
			
			if (dbRs.next())
				recordCount = dbRs.getInt(1);			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try						
		
	
		try {
			
			dbRs = dbStmt.executeQuery("SELECT * FROM salesdb.product");			
			
			System.out.println("record count " + recordCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try						
		
		try {					
			
			columnCount = dbRs.getMetaData().getColumnCount();
			
			
			Object data[][] = new Object[recordCount][columnCount];
			
			while (dbRs.next()) {
				
		        String[] record = new String[columnCount];
		        
		        for (int i = 0; i < columnCount; i++) {
		          //record[i] = dbRs.getString(i + 1);
		          data[dbRs.getRow()-1][i] = dbRs.getString(i + 1);
		          //System.out.println(dbRs.getRow() + " " + dbRs.getString(i + 1) + " " + i) ;
		        } //for
		        
		        //data[dbRs.getRow()] = record;
		        
		    } //while
			
			outData = data;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try				
				
		return outData;
		
	} //getProductData
	
	public void deleteProductData(Object IDObj) {
		
		String id = (String)IDObj;
		
		String deleteProductSQL = "DELETE FROM salesdb.product WHERE ID = ?";
		
		try {
			
			PreparedStatement dbPrep = dbCon.prepareStatement(deleteProductSQL);
		    dbPrep.setInt(1, Integer.valueOf(id));
			dbPrep.executeQuery();
			
		} catch(Exception e) {
			e.printStackTrace();
		} //try
		
	} //deleteProductData
	
    //----------------------------------------------------------------------------------------------  q
    /** 
     * Determines the number of rows in a <code>ResultSet</code>. Upon exit, if the cursor was not 
     * currently on a row, it is just before the first row in the result set (a call to 
     * {@link ResultSet#next()} will go to the first row). 
     * @param set The <code>ResultSet</code> to check (must be scrollable). 
     * @return The number of rows. 
     * @throws SQLException If the <code>ResultSet</code> is not scrollable. 
     * @see #hasSingleRow(ResultSet) 
     */  
    private int getRowCount(ResultSet set) throws SQLException {  
       int rowCount;  
       int currentRow = set.getRow();            // Get current row  
       
       //rowCount = set.last() ? set.getRow() : 0; // Determine number of rows
       
       if (set.last())
    	   rowCount = set.getRow();
       else
    	   rowCount = 0;
       
       if (currentRow == 0)                      // If there was no current row  
          set.beforeFirst();                     // We want next() to go to first row  
       else                                      // If there WAS a current row  
          set.absolute(currentRow);              // Restore it  
       return rowCount;  
    }  	//getRowCount
	
} //DBConnect 
