package test;

import java.util.ArrayList;
import java.util.Random;

public class Generation implements HolonList{
	Random rand = new Random();
	ArrayList<Holon>holons = new ArrayList<Holon>();
	ArrayList<Person>population = new ArrayList<Person>();
	int idCount = 0;
	double maxToken = 0;
	
	public Generation() {
		
	}
	
	public void makeHolonList() {
		//Holons
		holons.add(Art);
		holons.add(Construction);
		holons.add(Energy);
		holons.add(Judicial);
		holons.add(Military);
		holons.add(Education);
		holons.add(Reserve);
		holons.add(Crypto); //This may not exist?
		holons.add(Food);
		holons.add(Transportation);
		holons.add(Materials);
		holons.add(Health);
		holons.add(Industrials);
	}
	
	public Person genPerson() {	
		makeHolonList();
		
		ArrayList<Holon>h = new ArrayList<Holon>();
		int chance = rand.nextInt(99);
		if(chance < 5) {
			for(int j = 0; j < 4; j++) {
				int choice = rand.nextInt(13-j);
				h.add(holons.get(choice));
				holons.remove(choice);	
			}
		}
		else if(chance >= 5 && chance <20) {
			for(int j = 0; j < 3; j++) {
				int choice = rand.nextInt(13-j);
				h.add(holons.get(choice));
				holons.remove(choice);	
			}
		}
		else if(chance >= 20 && chance <55) {
			for(int j = 0; j < 2; j++) {
				int choice = rand.nextInt(13-j);
				h.add(holons.get(choice));
				holons.remove(choice);	
			}
		}
		else {
			int choice = rand.nextInt(13);
			h.add(holons.get(choice));
		}
		holons.clear();
		idCount++;
		return new Person("ox"+idCount, h);
	}
	
	public void addToPopulation(int numOfCitizens) {
		for(int i = 0; i < numOfCitizens; i++) {
			population.add(genPerson());
		}
	}
	
	public ArrayList<Holon> mostValuedHolon(){
		makeHolonList();
		
		Holon mostValued = holons.get(0);
		for(Holon h : holons) {
			if(mostValued.getPeopleInHolon()>h.getPeopleInHolon()) {
				mostValued = h;
			}
		}
		
		ArrayList<Holon> valuedHolon = new ArrayList<Holon>();
		valuedHolon.add(mostValued);
		holons.clear();
		return valuedHolon;
	}
	
	public Holon leastValuedHolon(){
		makeHolonList();
		
		Holon leastValued = holons.get(0);
		for(Holon h : holons) {
			if(leastValued.getPeopleInHolon()<h.getPeopleInHolon()) {
				leastValued = h;
			}
		}
		holons.clear();
		return leastValued;
	}
	
	public double holonSetAmount(ArrayList<Holon> hs) {
		double tokens = 0;
		for(Holon h : hs) {
			tokens += population.size()/(h.getPeopleInHolon()+1.0/hs.size());
		}
		tokens /= hs.size();
		return tokens;
	}
	
	public ArrayList<Holon> bestPosition(ArrayList<Person>population) { 
		makeHolonList();
		
		ArrayList<ArrayList<Holon>> all = new ArrayList<ArrayList<Holon>>();
		int count = 0;
		for(int i  = 0; i<holons.size(); i++) {
			ArrayList<Holon> temp = new ArrayList<Holon>();
			temp.add(holons.get(i));
			all.add(temp);
			count++;
		}
		for(int i  = 0; i<holons.size(); i++) {
			for(int j  = i; j<holons.size(); j++) {
				if(i!=j) {
					ArrayList<Holon> temp = new ArrayList<Holon>();
					temp.add(holons.get(i));
					temp.add(holons.get(j));
					all.add(temp);
					count++;
				}
			}
		}
		for(int i  = 0; i<holons.size(); i++) {
			for(int j  = i; j<holons.size(); j++) {
				if(i!=j) {
					for(int k  = j; k<holons.size(); k++) {
						if(j!=k) {
							ArrayList<Holon> temp = new ArrayList<Holon>();
							temp.add(holons.get(i));
							temp.add(holons.get(j));
							temp.add(holons.get(k));
							all.add(temp);
							count++;
						}
					}
				}
			}
		}
		for(int i  = 0; i<holons.size(); i++) {
			for(int j  = i; j<holons.size(); j++) {
				if(i!=j) {
					for(int k  = j; k<holons.size(); k++) {
						if(j!=k) {
							for(int l  = k; l<holons.size(); l++) {
								if(k!=l) {
									ArrayList<Holon> temp = new ArrayList<Holon>();
									temp.add(holons.get(i));
									temp.add(holons.get(j));
									temp.add(holons.get(k));
									temp.add(holons.get(l));
									all.add(temp);
									count++;
								}
							}
						}
					}
				}
			}
		}
		//System.out.println(count);
		
		all = runQuicksort(all);
		for(ArrayList<Holon> hs : all) {
			System.out.println(holonSetAmount(hs));
		}
		
		ArrayList<Holon> bestPosition = all.get(0);
		double max = 0;
		
		double tokens = 0;
		for(ArrayList<Holon> hs : all) {
			tokens = holonSetAmount(hs);
			if(tokens>max) {
				bestPosition = hs;
				max = tokens;
			}
		}
		maxToken = max;
		System.out.println("Best " + maxToken);
		for(Holon h : bestPosition) {
			System.out.print(h.name + "  ");
		}
		System.out.println();
		
		holons.clear();
		return bestPosition;
	}
	
	public ArrayList<Person> greedFactor(ArrayList<Person>population) {
		ArrayList<Holon> bestPosition = bestPosition(population);
		double avgGreed = 0;
		for(int i = 0; i < population.size(); i++) {
			double g = findGreedFactor(population.get(i));
			avgGreed+=g;
			if(g>rand.nextDouble()) {
				population.get(i).setHolons(bestPosition);
			}
		}
		System.out.println("Average greed factor " + avgGreed/population.size());
		return population;
	}
	
	public int populationAmount(int iteration) {
		return (int)(1000000/((1+Math.pow(Math.E, -(.09*iteration-5)))))-6691;
	}
	
	public double findGreedFactor(Person p) {
		return 1-(p.getWorth()/maxToken);
	}
	
	public ArrayList<Person> getPopulation(){
		return population;
	}
	
	double[] numbers;
	ArrayList<ArrayList<Holon>> allHoloncombos = new ArrayList<ArrayList<Holon>>();
	
	public ArrayList<ArrayList<Holon>> runQuicksort(ArrayList<ArrayList<Holon>> all) {
		numbers = new double[all.size()];
		allHoloncombos = all;
		int c = 0;
		for(ArrayList<Holon> hs : all) {
			numbers[c] = holonSetAmount(hs);
			c++;
		}
		
        quicksort(0, numbers.length - 1);
        return all;
    }
	
	private void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = (int) numbers[(low + (high-low)/2)];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }
    
    private void exchange(int i, int j) {
        double temp = numbers[i];
        ArrayList<Holon> t = allHoloncombos.get(i);
        numbers[i] = numbers[j];
        allHoloncombos.set(i, allHoloncombos.get(j));
        numbers[j] = temp;
        allHoloncombos.set(j, allHoloncombos.get(i));
    }
}
