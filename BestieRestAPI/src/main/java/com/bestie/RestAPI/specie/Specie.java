package com.bestie.RestAPI.specie;

public class Specie {

	int id_specie;
	String common_name;
	String scientific_name;
	
	public Specie() {
		this.id_specie = -1;
		this.common_name = "";
		this.scientific_name = "";
	}
	
	public int getId_specie() {
		return id_specie;
	}
	public void setId_specie(int id_specie) {
		this.id_specie = id_specie;
	}
	public String getCommon_name() {
		return common_name;
	}
	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}
	public String getScientific_name() {
		return scientific_name;
	}
	public void setScientific_name(String scientific_name) {
		this.scientific_name = scientific_name;
	}
	
}
