package com.bestie.RestAPI.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bestie.RestAPI.race.Race;
import com.bestie.RestAPI.race.RaceRepository;
import com.bestie.RestAPI.specie.Specie;
import com.bestie.RestAPI.specie.SpecieRepository;
import com.bestie.RestAPI.user.User;
import com.bestie.RestAPI.user.UserRepository;

@RestController
public class AdminController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	SpecieRepository specieRepo;
	@Autowired
	RaceRepository raceRepo;
	
	
	@GetMapping("/admin/{username}/{password}")
	public boolean checkAdmin(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		if(username.equals("mike73") && password.equals("123456")) {
			return true;
		}
		return false;
	}
	
	///////////////////////////////////////////////
	////// 	 Restituzione Tabelle Intere	 //////
	///////////////////////////////////////////////
	
	@GetMapping("/list/users")
	public List<User> getUserList(){
		return userRepo.getAllUsers();
	}
	
	@GetMapping("/list/species")
	public List<Specie> getRaceList(){
		return specieRepo.getAllSpecie();
	}
	
	@GetMapping("/list/races")
	public List<Race> getSpecieList(){
		return raceRepo.getAllRaces();
	}
	
}
