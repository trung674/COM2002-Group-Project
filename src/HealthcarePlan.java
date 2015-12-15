

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HealthcarePlan {
	
	private String name;
	private float cost;
	private int check_ups;
	private int hygiene_visits;
	private int repairs;
	
	public HealthcarePlan(String name, float cost, int check_ups, int hygiene_visits, int repairs){
		this.name = name;
		this.cost = cost;
		this.check_ups = check_ups;
		this.hygiene_visits = hygiene_visits;
		this.repairs = repairs;
	}
	
	public String getHealthcareName(){
		return this.name;
	}
	
	public float getMonthlyCost(){
		return this.cost;
	}
	
	public int getNumCheckUps(){
		return this.check_ups;
	}
	
	public int getNumHygieneVisits(){
		return this.hygiene_visits;
	}
	
	public int getNumRepairs(){
		return this.repairs;
	}
	
	
	/**
	 * Gets all the healthplans from the database
	 * @return ArrayList<HealthcarePlan> plans - All the plans from the database
	 * @throws SQLException 
	 */
	public static ArrayList<HealthcarePlan> getAllPlans() throws SQLException{
		
		ConnectDB connect = new ConnectDB();
		Connection con = connect.getCon();
		
		ArrayList<HealthcarePlan> plans = new ArrayList<HealthcarePlan>();
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM PLANS");
		ResultSet healthResults = stmt.executeQuery();
		
		while(healthResults.next()){
			String name = healthResults.getString(1);
			float cost = healthResults.getFloat(2);
			int check_ups = healthResults.getInt(3);
			int hygiene = healthResults.getInt(4);
			int repairs = healthResults.getInt(5);
			
			HealthcarePlan p = new HealthcarePlan(name, cost, check_ups, hygiene, repairs);
			plans.add(p);
		}
		
		stmt.close();
		con.close();
		
		return plans;
		
	}
}


