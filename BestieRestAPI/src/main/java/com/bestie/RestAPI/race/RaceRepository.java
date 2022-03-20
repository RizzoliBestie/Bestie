package com.bestie.RestAPI.race;

import java.util.List;

public interface RaceRepository {
	
	//Restituisce tutte le razze
	public List<Race> getAllRaces();
	
	public List<Race> getRaceByName(String name);
	
	public List<Race> getRaceBySpecie(int id_specie);
		
}
