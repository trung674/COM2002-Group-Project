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
}
