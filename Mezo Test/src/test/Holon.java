package test;

public class Holon{
	
	String name;
	double peopleInHolon = 0;
	
	public Holon(String name) {
		this.name = name;
	}
	
	public double getPeopleInHolon() {
		return peopleInHolon;
	}
	
	public void setPeopleInHolon(double pop) {
		peopleInHolon = pop;
	}
}
