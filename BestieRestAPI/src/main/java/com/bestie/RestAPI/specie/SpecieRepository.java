package com.bestie.RestAPI.specie;

import java.util.List;

public interface SpecieRepository {

	//Restituisce la lista di tutte le specie 
	public List<Specie> getAllSpecie();
	
	//Trova una specie tramite id
	
	public List<Specie> findSpecieById(int id_specie);
	
	//Aggiunge una nuova specie
	public void addSpecie(String common_name, String scientific_name);
	
	//Modifica una specie
	public void updateSpecie(String common_name, String scientific_name, int id_specie);
	
	//Elimina una specie
	public void deleteSpecie(int id_specie);
	
	public List<Specie> findSpecieByName(String common_name);
}
