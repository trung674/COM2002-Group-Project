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
	
	public ArrayList<Patient> getAllPatients() throws SQLException{
		
		ConnectDB connect = new ConnectDB();
		Connection con = connect.getCon();
		
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM PATIENTS");
		ResultSet patientResultSet = stmt.executeQuery();
		while(patientResultSet.next()){
			int id = patientResultSet.getInt(1);
			String forename = patientResultSet.getString(2);
			String surname = patientResultSet.getString(3);
			Date dob = patientResultSet.getDate(4);
			String phone_no = patientResultSet.getString(5);
			int address_id = patientResultSet.getInt(6);
			Boolean hasAddress = patientResultSet.wasNull();
			String healthCareName = patientResultSet.getString(7);
			Boolean hasHealthCare =  patientResultSet.wasNull();
			
			HealthcarePlan p = null;
			Address address = null;
			
			if(hasAddress){
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
				
				
			}
			
			if(hasHealthCare){
				PreparedStatement healthCareStmt = con.prepareStatement("SELECT * FROM PLANS WHERE `healthcare_name`= ?");
				healthCareStmt.setString(1, healthCareName);
				ResultSet healthCareResultSet = healthCareStmt.executeQuery();
				while(healthCareResultSet.next()){
					String houseNum = healthCareResultSet.getString(2);
					String streetName = healthCareResultSet.getString(3);
					String district = healthCareResultSet.getString(4);
					String city = healthCareResultSet.getString(5);
					String postcode = healthCareResultSet.getString(6);
				}
			}
			
		}
		
		con.close();
		
		return null;
	}
	
	public static void main(String[] args) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d = sdf.parse("09/12/1997");
		java.sql.Date sqlDate = new java.sql.Date(d.getTime());

		Patient paul = new Patient(1001,"Mr","Bob","Dylan",sqlDate,"07222333222",null,null);
		
		System.out.println(sqlDate);
		System.out.println(paul.getAge());
	}
	
	public String getForename(){
		return this.forename;
	}
	
	public String getSurname(){
		return this.surname;
	}
	
	
	
	
}



