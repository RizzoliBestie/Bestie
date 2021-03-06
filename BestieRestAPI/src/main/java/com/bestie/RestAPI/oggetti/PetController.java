package com.bestie.RestAPI.oggetti;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {
	
	@Autowired
	private PetService petService;
	
	@RequestMapping("/pets")
	public List<Pet> getAllPets(){
		System.out.println("Pet successo");
		return petService.getAllPets();
	}
	
	@RequestMapping("/pets/{id}")
	public Pet findPetById(@PathVariable long id) {
		Pet pet= petService.findPetById(id).get(0);
		return pet;
	}
	
	@PostMapping ("/newPet/{pet}")
	public boolean newPet(@RequestBody Pet pet) {
		System.out.println("Pet aggiunto");
		return petService.newPet(pet);
	}
	
	@PutMapping("/update/pet/{petObject}")
	public boolean updatePet(@RequestBody Pet pet) {
		System.out.println("Pet updated");
		petService.updatePet(pet);
		return true;
	}
	
	@RequestMapping("/pets/user/{id_user}")
	public List<Pet> getPetByIdUser(@PathVariable int id_user){
		System.out.println("Pets by Id User getted");
		return petService.getPetByIdUser(id_user);
	}
}
