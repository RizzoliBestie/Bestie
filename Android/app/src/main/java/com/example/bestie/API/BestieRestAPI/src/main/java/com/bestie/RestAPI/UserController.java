package com.bestie.RestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserRepository repo;
	
	@RequestMapping(method=RequestMethod.GET, value = "/login/{id}+{username}")
	public String getUser(@PathVariable(value="id") Integer id, @PathVariable(value="username") String username) {
		if(repo.findById(id).size()==0 || repo.findByUsername(username).size()==0)
			return "Nome utente o password non corretti";
		else 
			return "Credenziali corrette";
	}
	
}