import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Appointment {
	

	private Time startTime;
	private Time endTime;
	private int patientID;
	private String partner;
	private String type;
	private Date date;
	
	public Appointment(){
		this.startTime = null;
		this.endTime = null;
		this.patientID = 0;
		this.partner = null;
		this.type = null;
		this.date = null;
	}
	
	public Appointment(Time sTime, Time eTime, int id,String p, String type, Date d){
		this.startTime = sTime;
		this.endTime = eTime;
		this.patientID = id;
		this.partner = p;
		this.type = type;
		this.date = d;
	}
	
	public Appointment(Time sTime, Time eTime, String p, Date d){
		this.startTime = sTime;
		this.endTime = eTime;
		this.patientID = 0;
		this.partner = p;
		this.type = null;
		this.date = d;
	}
	
	public  boolean isHoliday(){
		return this.type.equals(null);
	}
	
	public Time getStartTime(){
		return startTime;
	}
	
	public Time getEndTime(){
		return endTime;
	}
	
	public String getPartner(){
		return partner;
	}
	
	public String getType(){
		return type;
	}
	
	public Date getDate(){
		return date;
	}
	
	public int getID(){
		return patientID;
	}
	
	public Patient getPatient(int id){
		return null;
	}
	
	
	public static void addAppointment(Appointment a){
		
	}
	
	public static void cancelAppointment(Appointment a) throws SQLException{
		
		ConnectDB connect = new ConnectDB();
		Connection con = connect.getCon();
		
		PreparedStatement stmt = con.prepareStatement("DELETE FROM CALENDER WHERE date = ? AND start_time = ?");
		stmt.setDate(1, a.getDate());
		stmt.setTime(2, a.getStartTime());
		stmt.executeUpdate();
		
		con.close();

	}
	
	public static ArrayList<Appointment> getAppointments(Calendar s, Calendar e) throws SQLException{
		
		ArrayList<Appointment> apps = new ArrayList<Appointment>();

		Calendar start = (Calendar) s.clone();
		Calendar end = (Calendar) e.clone();
		
		start.add(Calendar.DATE, -1);
		end.add(Calendar.DATE, +1);
		java.sql.Date sdate = new java.sql.Date(start.getTimeInMillis());
		java.sql.Date edate = new java.sql.Date(end.getTimeInMillis());
		
		System.out.println(sdate);
		System.out.println(edate);
		
		ConnectDB connect = new ConnectDB();
		Connection con = connect.getCon();
		
		  
	    PreparedStatement stmt = con.prepareStatement(
	    		 "SELECT * FROM CALENDER");
	      
	    ResultSet rs = stmt.executeQuery();
	    while(rs.next()){
	    	Date date = rs.getDate(1);
	    	Time sTime = rs.getTime(2);
			Time eTime = rs.getTime(3);
			int patientId = rs.getInt(4);
			if(rs.wasNull()){
				patientId = 0;
			}
			String partner = rs.getString(5);
			String type = rs.getString(6);
			if(rs.wasNull()){
				type = "Holiday";
			}
	
			
			if(type.equals("Holiday")){
				Appointment a = new Appointment(sTime,eTime,partner,date);
				System.out.println(a.toString());
				apps.add(a);
			}else{
				Appointment a = new Appointment(sTime,eTime,patientId,partner,type,date);
				System.out.println(a.toString());
				apps.add(a);
			}
	    }
		
	    
	    
	    ArrayList<Appointment> inRangeAppointments = new ArrayList<Appointment>();
	    Iterator<Appointment> itr = apps.iterator();
		while(itr.hasNext()){
			Appointment a = itr.next(); 
			if(!(sdate.after(a.getDate()))  && !(edate.before(a.getDate()))){
				inRangeAppointments.add(a);
  	      	}
        }  
	    
	   con.close();
	
		// 
		
	return inRangeAppointments;
		
	}
	
	public Patient getPatient() throws SQLException{
		
		// Patient
		int id = 0;
		String title = null;
		String forename = null;
		String surname = null;
		Date dob = null;
		String phone_no = null;
		int address_id = 0;
		String healthCareName = null;
		
		// Address
		String hNum = null;
		String streetName = null;
		String district = null;
		String city = null;
		String postcode = null ;
		
		// Healthcare plan
		
		 String name = null;
		 float cost = 0.0f;
		 int check_up = 0;
		 int hygiene_visits = 0;
		 int repairs = 0;
		
		
		if (this.getType() == null){
			return null;
		}else{
			ConnectDB connect = new ConnectDB();
			Connection con = connect.getCon();
			
			
			
			
			 PreparedStatement stmt = con.prepareStatement(
		    		 "SELECT * FROM PATIENTS WHERE patient_id = ?");
			 
			 stmt.setInt(1, this.getID());
		     ResultSet rs = stmt.executeQuery();
		     while(rs.next()){
		    	 id = rs.getInt(1);
		    	 title = rs.getString(2);
		    	 forename = rs.getString(3);
		    	 surname = rs.getString(4);
		    	 dob = rs.getDate(5);
		    	 phone_no = rs.getString(6);
		    	 address_id = rs.getInt(7);
		    	 healthCareName = rs.getString(8);
		     }
		     
			 PreparedStatement stmt2 = con.prepareStatement(
		    		 "SELECT * FROM ADDRESS WHERE address_id = ?");
			 
			 stmt2.setInt(1, address_id);
			 
			 ResultSet rs2 = stmt2.executeQuery();
		     while(rs2.next()){
		    	 hNum = rs2.getString(2);
		    	 streetName = rs2.getString(3);
		    	 district = rs2.getString(4);
		    	 city = rs2.getString(5);
		    	 postcode = rs2.getString(6);
		     }
		     
			 PreparedStatement stmt3 = con.prepareStatement(
		    		 "SELECT * FROM PLANS WHERE healthcare_name = ?");
			 
			 stmt3.setString(1, healthCareName);
			 
			 ResultSet rs3 = stmt3.executeQuery();
		     while(rs3.next()){
		    	 name = rs3.getString(1);
		    	 cost = rs3.getFloat(2);
		    	 check_up = rs3.getInt(3);
		    	 hygiene_visits = rs3.getInt(4);
		    	 repairs = rs3.getInt(5);
		     }
		     
		     
		     Address address = new Address(hNum,streetName,district,city,postcode);
		     HealthcarePlan health = new HealthcarePlan(name,cost,check_up,hygiene_visits,repairs);
		     Patient p = new Patient(id,title,forename,surname,dob,phone_no,address,health);
		     con.close();
		     return p;
		     
	
		    		 
		}
		
	}
	
	public String toString(){
		return this.date + " " + this.startTime + " " + this.endTime + " " + this.partner + " " + this.patientID + " " + this.type;
		
	}
}
