package test;

import java.util.ArrayList;
import java.util.Random;

import distribution.Engine;

public class Run implements HolonList{
	
	public static void printSociety(ArrayList<Person>population, ArrayList<Holon>holons) {
		for(Holon h : holons) {
			System.out.println(h.name + ": " + h.getPeopleInHolon() + " people");
		}
		
		double[] numsOfHolon = {0,0,0,0};
		for(Person p : population) {
			numsOfHolon[p.getHolons().size()-1]++;
		}
		for(int i = 0; i < 4; i++) {
			System.out.println((i+1) + " Holons " + numsOfHolon[i]/population.size());
		}
		System.out.println();
		
		System.out.println(population.get(0).getID() + " has " + population.get(0).getBalance() + " tokens");
		for(Holon h : population.get(0).getHolons()) {
			System.out.println(h.name);
		}
		System.out.println();
		System.out.println(population.get(population.size()/2).getID() + " has " + population.get(population.size()/2).getBalance() + " tokens");
		for(Holon h : population.get(population.size()/2).getHolons()) {
			System.out.println(h.name);
		}
		System.out.println();
		System.out.println(population.get(population.size()-1).getID() + " has " + population.get(population.size()-1).getBalance() + " tokens");
		for(Holon h : population.get(population.size()-1).getHolons()) {
			System.out.println(h.name);
		}
		System.out.println();
		/*
		for(int i = 0; i < population.size(); i++) {
			if(i%1 == 0) {
				System.out.println(population.get(i).getID() + " has " + population.get(i).getBalance() + " tokens");
				for(Holon h : population.get(i).getHolons()) {
					System.out.println(h.name);
				}
				System.out.println();
			}
		}
		*/
	}
	
	public static void main(String[] args) {
		/*
		ArrayList<Person>population = new ArrayList<Person>();
		
		//Person 1
		ArrayList<Holon> p1Holons = new ArrayList<Holon>();
		p1Holons.add(Education);
		p1Holons.add(Industrials);
		population.add(new Person("J_Bahm", p1Holons));
		
		//Person 2
		ArrayList<Holon> p2Holons = new ArrayList<Holon>();
		p2Holons.add(Reserve);
		population.add(new Person("xxTomtacion",  p2Holons));
		
		//Person 3
		ArrayList<Holon> p3Holons = new ArrayList<Holon>();
		p3Holons.add(Food);
		population.add(new Person("Dom_Yee",  p3Holons));
		
		//Person 4
		ArrayList<Holon> p4Holons = new ArrayList<Holon>();
		p4Holons.add(Health);
		population.add(new Person("Alex_Walkon",  p4Holons));
		*/
		ArrayList<Holon>holons = new ArrayList<Holon>();
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
		
		//Simulation
		Generation gen = new Generation();
		/*
		gen.addToPopulation(100);
		ArrayList<Person>population = gen.getPopulation();
		Engine allocation = new Engine();
		
		allocation.distribute(population);
		
		//printSociety(population, holons);
		ArrayList<Holon>valuedHolon = gen.mostValuedHolon(population);
		Holon mostValued = valuedHolon.get(0);
		System.out.println("Most valued holon " + mostValued.name + " with " + population.size()/mostValued.getPeopleInHolon() + " tokens");
		System.out.println("Least valued holon " + gen.leastValuedHolon(population).name + " with " + population.size()/gen.leastValuedHolon(population).getPeopleInHolon() + " tokens");
		System.out.println();
		
		population = gen.greedFactor(.3, population);
		
		System.out.println("After greed");
		System.out.println();
		allocation.distribute(population);
		
		//printSociety(population, holons);
		valuedHolon = gen.mostValuedHolon(population);
		mostValued = valuedHolon.get(0);
		System.out.println("Most valued holon " + mostValued.name + " with " + population.size()/mostValued.getPeopleInHolon() + " tokens");
		System.out.println("Least valued holon " + gen.leastValuedHolon(population).name + " with " + population.size()/gen.leastValuedHolon(population).getPeopleInHolon() + " tokens");
		*/
		
		ArrayList<Person>population = gen.getPopulation();
		Engine allocation = new Engine();
		for(int i  = 0; i<120; i+=90) {
			gen.addToPopulation(gen.populationAmount(i)-population.size());
			population = gen.getPopulation();
			System.out.println("Iteration " + (i+1));
			System.out.println("Population size: " + population.size());
			allocation.distribute(population);
			//printSociety(population, holons);
			System.out.println();
			population = gen.greedFactor(population);
			allocation.distribute(population);
			//printSociety(population, holons);
			//System.out.println("Most valued holon " + mostValued.name + " with " + population.size()/mostValued.getPeopleInHolon() + " tokens");
			//System.out.println("Least valued holon " + gen.leastValuedHolon(population).name + " with " + population.size()/gen.leastValuedHolon(population).getPeopleInHolon() + " tokens");
			System.out.println();
		}
		
	}
}
