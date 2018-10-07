package distribution;

import java.util.ArrayList;
import test.Person;
import test.Holon;
import test.HolonList;

public class Engine implements HolonList{

	double resourcePool = 0;
	
	public Engine() {
	}
	
	public void distribute(ArrayList<Person>population) {
		resetHolons();
		for(Person p : population) {
			for(Holon h : p.getHolons()) {
				h.setPeopleInHolon(h.getPeopleInHolon() + 1.0/p.getHolons().size());
			}
		}
		for(Person p : population) {
			double tokens = 0;
			for(Holon h : p.getHolons()) {
				tokens += population.size()/h.getPeopleInHolon();
			}
			p.setWorth(tokens/p.getHolons().size());
		}
	}
	
	public void resetHolons() {
		Art.setPeopleInHolon(0);
		Construction.setPeopleInHolon(0);
		Energy.setPeopleInHolon(0);
		Judicial.setPeopleInHolon(0);
		Military.setPeopleInHolon(0);
		Education.setPeopleInHolon(0);
		Reserve.setPeopleInHolon(0);
		Crypto.setPeopleInHolon(0);
		Food.setPeopleInHolon(0);
		Transportation.setPeopleInHolon(0);
		Materials.setPeopleInHolon(0);
		Health.setPeopleInHolon(0);
		Industrials.setPeopleInHolon(0);
	}
	
	public void addToPool(double amount) {
		resourcePool += amount;
	}
	
}
