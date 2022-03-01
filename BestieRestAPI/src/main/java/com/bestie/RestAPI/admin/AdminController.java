package com.bestie.RestAPI.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	@Autowired
	AdminRepository repo;
	
	@GetMapping("/admin/{username}/{password}")
	public boolean checkAdmin(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		return repo.checkAdmin(username, password);
	}
	
	
}
