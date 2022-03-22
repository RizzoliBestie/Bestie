package com.bestie.RestAPI.race;

import java.util.List;

public interface RaceRepository {
	
	//Restituisce tutte le razze
	public List<Race> getAllRaces();
	
	public List<Race> getRaceByName(String name);
	
	public List<Race> getRaceBySpecie(int id_specie);
	
	public List<Race> getRaceById(int id_race);
		
	public void deleteRace(int id_race);
	
	public void updateRace(String name, String information, String size, int id_race);
	
}
