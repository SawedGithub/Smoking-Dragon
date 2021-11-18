package SmokingDragon;



public class shopList {
	private String name;
	public shopList(String name, int income, int cost) {
		super();
		this.name = name;
		this.income = income;
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	private int income;
	private int cost;
	public static void main(String[] args) {
		
	}

}
