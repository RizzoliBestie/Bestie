package com.bestie.RestAPI;

import java.util.List;

public interface UserRepository {

	//Trova un utente tramite id 
	public List<User> findById(Integer id);
		
	//Trova un utente tramite username
	public List<User> findByUsername(String username);
	
	//Trova un utente tramite email
	public List<User> findByMail(String email);
	
	
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
	
	
}
