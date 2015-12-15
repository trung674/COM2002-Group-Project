package project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	public static void main(String[] args) throws SQLException{
	    
	    Connection con = null; // connection to a database
	    try {

	      String DB="jdbc:mysql://stusql.dcs.shef.ac.uk/team008?user=team008&password=fff8f017";
	      con = DriverManager.getConnection(DB);

	      Statement stmt = con.createStatement();

	     
	     
	      
	      
	      String drop = "DROP TABLE";
	      //stmt.executeUpdate(drop);
	     
	      
	      /*   
	      String patients = "CREATE TABLE PATIENTS " +
	    		  			"(patient_id INTEGER not NULL," +
	    		  			"title VARCHAR(35) not NULL, " +
	    		  			" forename VARCHAR(35) not NULL, " +
	    		  			" surname VARCHAR(35) not NULL, " +
	    		  			" date_of_birth DATE not NULL, " +
	    		  			"phone_nos VARCHAR(12) not NULL," +
	    		  			"address_id INTEGER not NULL," +
	    		  			"healthcare_name VARCHAR(35)," +
	    		  			" PRIMARY KEY (patient_id),"+ 
	    		  			"FOREIGN KEY (address_id) REFERENCES ADDRESS(address_id)," +
	    		  			"FOREIGN KEY (healthcare_name) REFERENCES PLANS(healthcare_name))";
	      
	      
	      String address = "CREATE TABLE ADDRESS " +
			  				"(address_id INTEGER not NULL," + 
			  				"house_number VARCHAR(35) not NULL, " +
			  				" street_name VARCHAR(35) not NULL, " +
			  				" district VARCHAR(35) not NULL, " +
			  				" city VARCHAR(35) not NULL, " +
			  				" post_code VARCHAR(8) not NULL, " +
			  				" PRIMARY KEY (address_id ))";
	       
	       
	      String log = "CREATE TABLE LOGS " +
	    		  		"(treatment_name VARCHAR(35) not NULL," +
	    		  		"patient_id INTEGER not NULL, " +
	    		  		"extra_visit BOOLEAN not NULL,"+
	    		  		"partner_type VARCHAR(10) not NULL,"+
	    		  		" PRIMARY KEY (patient_id),"+
	    		  		"FOREIGN KEY (treatment_name) REFERENCES TREATMENTS(treatment_name))";   
	      
	      String calender = "CREATE TABLE CALENDER " +
	    		  				"(start_time TIME not NULL, " +
	    		  				" end_time TIME not NULL, " +
	    		  				"patient_id INTEGER," +
	    		  				"partner_type VARCHAR(8) not NULL," +
	    		  				" date DATE not NULL, " +
	    		  				"appointment_type VARCHAR(35),"+
	    		  				" PRIMARY KEY (start_time, date ))";
	      

	      String treatments = "CREATE TABLE TREATMENTS " +
	    		  				"(treatment_name VARCHAR(35) not NULL," +
	    		  				"cost FLOAT not NULL, " +
	    		  				" PRIMARY KEY (treatment_name))";   
	      
	      String healthcare = "CREATE TABLE PLANS " +
	    		  				"(healthcare_name VARCHAR(35) not NULL," +
	    		  				"monthly_cost FLOAT not NULL, " +
	    		  				"check_up INT not NULL," +
	    		  				"hygiene_visit INT not NULL,"+
	    		  				"reapirs INT not NULL,"+
	    		  				" PRIMARY KEY (healthcare_name))"; 
	      
	      
	       stmt.executeUpdate(address);
	       stmt.executeUpdate(patients);
	       stmt.executeUpdate(healthcare);
	       stmt.executeUpdate(treatments);
	       stmt.executeUpdate(calender);
	      stmt.executeUpdate(log);
	   
	      */ 
		      
	      
	    }
	    catch (SQLException ex) {
	    ex.printStackTrace(); }
	    finally {
	    if (con != null) con.close();


	    }
	    }

}
