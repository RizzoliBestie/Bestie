package com.bestie.RestAPI;

import java.util.List;

public interface UserRepository {

	//Trova un utente tramite id 
	public List<User> findById(Integer id);
		
	//Trova un utente tramite username
	public List<User> findByUsername(String username);
	
	//Trova un utente tramite email
	public List<User> findByMail(String email);
}
