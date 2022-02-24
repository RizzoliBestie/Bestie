package com.bestie.RestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserRepository repo;
	
	@GetMapping("/login/{username}/{password}")
	public boolean getUserLogin(@PathVariable String username, @PathVariable String password) {
		if(repo.checkLogin(username, password).size()==0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@GetMapping("/signin/{username}/{email}")
	public boolean getUserSignin(@PathVariable String username, @PathVariable String email) {
		if(repo.checkSignin(username, email).size()==0)
			return false;
		else
			return true;
	}
	
	@PostMapping("/signin/{username}/{email}/{password}/{phone_number}")
	public boolean newUser(@PathVariable("username") String username,@PathVariable("email") String email,@PathVariable("password") String password,@PathVariable("phone_number") String phone_number) {
		return repo.register(username, email, password, phone_number);
	}
	
}
