package com.bestie.RestAPI.race;

public class Race {

	int id_race;
	int id_specie;
	String name;
	String information;
	String size;
	String fur_type;
	
	public Race() {
		this.id_race = -1;
		this.id_specie = -1;
		this.name = "";
		this.information = "";
		this.size = "";
		this.fur_type = "";
	}
	
	public int getId_race() {
		return id_race;
	}
	public void setId_race(int id_race) {
		this.id_race = id_race;
	}
	public int getId_specie() {
		return id_specie;
	}
	public void setId_specie(int id_specie) {
		this.id_specie = id_specie;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFur_type() {
		return fur_type;
	}
	public void setFur_type(String fur_type) {
		this.fur_type = fur_type;
	}
	
}
