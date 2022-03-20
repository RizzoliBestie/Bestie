package com.bestie.RestAPI.specie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpecieController {
	
	@Autowired
	JdbcSpecieRepository jdbcSpecieRepository;
	
	@RequestMapping("/list/species")
	public List<Specie> getAllSpecies(){
		System.out.println("Get all species");
		return jdbcSpecieRepository.getAllSpecie();
	}

}
