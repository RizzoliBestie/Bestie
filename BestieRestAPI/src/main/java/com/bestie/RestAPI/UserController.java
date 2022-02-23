package com.bestie.RestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserRepository repo;
	
	@RequestMapping(method=RequestMethod.GET, value = "/login")
	public String getUser(@RequestBody String username, @RequestBody String password) {
		if(repo.checkLogin(username, password).size()==0)
			return "Nome utente o password non corretti";
		else 
			return "Credenziali corrette";
	}
	
}