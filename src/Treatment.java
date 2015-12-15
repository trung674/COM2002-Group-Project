package project;

public class Treatment {
	private String name;
	private float cost;
	
	public Treatment(String name,float cost){
		this.name = name;
		this.cost = cost;
	}
	
	public String getName(){
		return this.name;
	}
	
	public float getCost(){
		return this.cost;
	}
	
	public static String getType(String tName){
		if(tName.equals("Check-up")){
			return "Check-up";
		}else if(tName.equals("Teeth Cleaning")){
			return "Hygiene";
		}else{
			return "Repair";
		}
	}
}
