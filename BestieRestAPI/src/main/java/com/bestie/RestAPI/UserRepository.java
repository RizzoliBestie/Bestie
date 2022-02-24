package com.bestie.RestAPI;

import java.util.List;

import com.bestie.RestAPI.oggetti.Pet;
import com.bestie.RestAPI.oggetti.User;

public interface UserRepository {

	//Trova un utente tramite id 
	public List<User> findUserById(Integer id);
		
	//Trova un utente tramite username
	public List<User> findUserByUsername(String username);
	
	//Trova un utente tramite email
	public List<User> findUserByMail(String email);
	
	//Trova una razza tramite 
	
	
	/*
	 * 		METODI D'AUTENTICAZIONE
	 */
	
    //Controlla che le credenziali del login siano corrette
	public List<User> checkLogin(String username, String password);
	
    //Controlla che username ed email siano unici
	public List<User> checkSignin(String username, String email);
	
	//Registrazione dell'utente
	public boolean register(String username, String email, String password, String phone_number);
	
	/*
	 * 		GESTIONE PET
	 */
	
	public boolean newPet(String owner, String race, String nickname, double weight, boolean gender);
	
	//public List<Pet> getPet();
	
}
