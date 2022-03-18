package com.bestie.RestAPI.oggetti;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {
	
	@Autowired
	private PetService petService;
	
	@RequestMapping("/pets")
	public List<Pet> getAllPets(){
		return petService.getAllPets();
	}
	
	@RequestMapping("/pets/{id}")
	public List<Pet> getPetById(@PathVariable long id) {
		return petService.getPetById(id);
	}

}
