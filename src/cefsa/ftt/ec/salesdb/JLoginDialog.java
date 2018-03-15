package cefsa.ftt.ec.salesdb;

import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JLoginDialog { 

	private JDBConnect dbConnect;

	public void setDBConnect(JDBConnect dbc){
		this.dbConnect = dbc;		
	} //setDBConnect
	
	public JLoginDialog(JDBConnect dbc) {
		
		setDBConnect(dbc);

		JTextField dbUserField = new JTextField();
		JTextField dbPasswordField = new JPasswordField();
		JTextField dbConnectionField = new JTextField();
		JTextField dbDriverField = new JTextField();

		Object[] msg = {"User Name:",  dbUserField,
						"Password:",   dbPasswordField,
						"Connection:", dbConnectionField,
						"Driver:",     dbDriverField};

		dbConnectionField.setText(dbConnect.getDbConString());
		dbDriverField.setText(dbConnect.getDbDriver());
		dbUserField.setText(dbConnect.getDbUser());
		
		JOptionPane op = new JOptionPane(
			msg,
			JOptionPane.QUESTION_MESSAGE,
			JOptionPane.OK_CANCEL_OPTION,
			null,
			null);

		JDialog dialog = op.createDialog("System Login");
		
		dialog.setVisible(true);

		int result = JOptionPane.OK_OPTION;

		try {
		    result = ((Integer)op.getValue()).intValue();
		} catch(Exception uninitializedValue){
			
		} //try

		if(result == JOptionPane.OK_OPTION) {
			
			System.out.println("Login user: " + dbUserField.getText() + " : " + new Date());
			
			dbConnect.setDbConString(dbConnectionField.getText());
			dbConnect.setDbDriver(dbDriverField.getText());
			dbConnect.setDbUser(dbUserField.getText());
			dbConnect.setDbPassword(dbPasswordField.getText());								
			
		} else {
			
			System.out.println("Canceled: " + new Date());
			
			try {
				dbConnect.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			} //try
			
			System.exit(0);
			
		} //if		

	} //LoginDialog Constructor
		
} //LoginDialog