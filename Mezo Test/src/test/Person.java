package test;

import java.util.ArrayList;

public class Person implements HolonList{
	
	String id;
	double worth, spent, balance = 0;
	ArrayList<Holon> holons;
	
	public Person(String id, ArrayList<Holon> holons) {
		this.id = id;
		this.holons = holons;
	}
	
	public void setHolons(ArrayList<Holon> h) {
		holons = h;
	}
	
	public ArrayList<Holon> getHolons(){
		return holons;
	}
	
	public String getID() {
		return id;
	}
	
	public double getBalance() {
		return worth-spent;
	}
	
	public double getWorth() {
		return worth;
	}
	
	public void setWorth(double value) {
		worth = value;
	}
	
	public void spend(double amount) {
		spent += amount;
	}
	
	public void returnFromPool(double amount) {
		spent -= amount;
	}
}
