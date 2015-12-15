package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Patient {
	
	public static int MILLISECONDS_IN_MINUITE = 1000 * 60;
	public static int MILLISECONDS_IN_HOUR = MILLISECONDS_IN_MINUITE * 60;
	public static int MILLISECONDS_IN_DAY = MILLISECONDS_IN_HOUR * 24;
	public static int MILLISECONDS_IN_YEAR = 864000000 * 365;
	
	private int id;
	private String title;
	private String forename;
	private String surname;
	private Date dob;
	private String phone_no;
	private Address address;
	private HealthcarePlan hp;
	private ArrayList<Appointment> apps;
	//private ArrayList<Log> logs;
	
	public Patient(int id, String title, String forename, String surname, Date dob, String phone_no,Address address,HealthcarePlan p){
		this.id = id;
		this.title = title;
		this.forename = forename;
		this.surname = surname;
		this.dob = dob;
		this.phone_no = phone_no;
		this.address = address;
		this.hp = p;
	}
	
	public Patient() {
		this.id = 0;
		this.title = null;
		this.forename = null;
		this.surname = null;
		this.dob = null;
		this.phone_no = null;
		this.address = null;
		this.hp = null;
	}

	public int getAge(){
		Calendar today = Calendar.getInstance();
		Calendar dob = Calendar.getInstance();
		dob.setTime(this.dob);
		int years_diff = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		if(today.get(Calendar.MONTH) > dob.get(Calendar.MONTH)){
			years_diff--;
		}
		
		if(today.get(Calendar.MONTH) == dob.get(Calendar.MONTH) && today.get(Calendar.DATE) > dob.get(Calendar.DATE)){
			years_diff--;
		}
		
	    return years_diff;
		
		
	}
	
	public void setHealthPLan(HealthcarePlan p){

	}
	
	public static ArrayList<Patient> getAllPatients() throws SQLException{
		
		ConnectDB connect = new ConnectDB();
		Connection con = connect.getCon();
		
		ArrayList<Patient> allPatients = new ArrayList<Patient>();
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM PATIENTS");
		ResultSet patientResultSet = stmt.executeQuery();
		while(patientResultSet.next()){
			int id = patientResultSet.getInt(1);
			String title = patientResultSet.getString(2);
			String forename = patientResultSet.getString(3);
			String surname = patientResultSet.getString(4);
			Date dob = patientResultSet.getDate(5);
			String phone_no = patientResultSet.getString(6);
			int address_id = patientResultSet.getInt(7);
			Boolean hasAddress = patientResultSet.wasNull();
			String healthCareName = patientResultSet.getString(8);
			Boolean hasHealthCare =  patientResultSet.wasNull();
			
			HealthcarePlan plan = null;
			Address address = null;
			
			if(!hasAddress){
				PreparedStatement addressStmt = con.prepareStatement("SELECT * FROM ADDRESS WHERE `address_id`= ?");
				addressStmt.setInt(1, address_id);
				ResultSet addressResultSet = addressStmt.executeQuery();
				while(addressResultSet.next()){
					String houseNum = addressResultSet.getString(2);
					String streetName = addressResultSet.getString(3);
					String district = addressResultSet.getString(4);
					String city = addressResultSet.getString(5);
					String postcode = addressResultSet.getString(6);
					address = new Address(houseNum,streetName,district,city,postcode);
				}
				
				addressStmt.close();
			}
			
			if(!hasHealthCare){
				PreparedStatement healthCareStmt = con.prepareStatement("SELECT * FROM PLANS WHERE `healthcare_name`= ?");
				healthCareStmt.setString(1, healthCareName);
				ResultSet healthCareResultSet = healthCareStmt.executeQuery();
				while(healthCareResultSet.next()){
					Float monthlyCost = healthCareResultSet.getFloat(2);
					int noCheckups = healthCareResultSet.getInt(3);
					int noHygieneVisits = healthCareResultSet.getInt(4);
					int noRepairs = healthCareResultSet.getInt(5);
					plan = new HealthcarePlan(healthCareName,monthlyCost,noCheckups,noHygieneVisits,noRepairs);
				}
				
				healthCareStmt.close();
			}
			
			Patient p = new Patient(id,title,forename,surname,dob,phone_no,address,plan);
			allPatients.add(p);
		}
		stmt.close();
		con.close();
		
		return allPatients;
	}
	
	
	public String getForename(){
		return this.forename;
	}
	
	public String getSurname(){
		return this.surname;
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getPhoneNumber(){
		return this.phone_no;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public Date getDateOfBirth(){
		return this.dob;
	}
	
	public Address getAdddress(){
		return this.address;
	}
	
	public HealthcarePlan getHealthcarePlan(){
		return this.hp;
	}
	
	public static void main(String[] args) throws SQLException{
		try{
			ArrayList<Patient> patients = Patient.getAllPatients();
			System.out.println(patients.get(0));
			System.out.println(patients.size());
		}catch(SQLException e){
			System.out.println("YO");
		}finally{
			
		}
	}
	
	public String toString(){
		return this.getForename();
	}
	
	
	public void updateHealthCarePlan(String healthCareName) throws SQLException{
		
		ConnectDB connect = new ConnectDB();
		Connection con = connect.getCon();
		
		if(healthCareName == null){
			PreparedStatement updateHealthCarePlan = 
					con.prepareStatement("UPDATE PATIENTS SET healthcare_name = NULL WHERE patient_id = ?");
			updateHealthCarePlan.setInt(1,this.getID());
			updateHealthCarePlan.executeUpdate();
			updateHealthCarePlan.close();
			con.close();
		}else{
			PreparedStatement updateHealthCarePlan = 
				con.prepareStatement("UPDATE PATIENTS SET healthcare_name = ? WHERE patient_id = ?");
			updateHealthCarePlan.setString(1,healthCareName);
			updateHealthCarePlan.setInt(2,this.getID());
			updateHealthCarePlan.executeUpdate();
			updateHealthCarePlan.close();
			con.close();
		}
	}
}



