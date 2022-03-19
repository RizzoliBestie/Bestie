package com.bestie.RestAPI.oggetti;

import java.util.Date;

public class Pet {

	private int id_pet;
	private int id_user;
	private int id_race;
	private String name;
	private double weight;
	private byte gender; 
	private Date birthDate;
	private Date lastMeal; 
	private Date lastWalk; 
	private byte sterilized;
	
	public byte getGender() {
		return gender;
	}
	
	public byte getSterilized() {
		return sterilized;
	}
	public void setSterilized(byte sterilized) {
		this.sterilized = sterilized;
	}
	public int getId_pet() {
		return id_pet;
	}
	public void setId_pet(int id_pet) {
		this.id_pet = id_pet;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_race() {
		return id_race;
	}
	public void setId_race(int id_race) {
		this.id_race = id_race;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String isGender() {
		return gender;
	}
	public void setGender(byte gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getLastMeal() {
		return lastMeal;
	}
	public void setLastMeal(Date lastMeal) {
		this.lastMeal = lastMeal;
	}
	public Date getLastWalk() {
		return lastWalk;
	}
	public void setLastWalk(Date lastWalk) {
		this.lastWalk = lastWalk;
	}
	
}
