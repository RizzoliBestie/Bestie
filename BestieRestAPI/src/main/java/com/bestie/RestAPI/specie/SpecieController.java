package com.bestie.RestAPI.specie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestie.RestAPI.user.User;

@RestController
public class SpecieController {
	
	@Autowired
	JdbcSpecieRepository jdbcSpecieRepository;
	
	@GetMapping("/list/species")
	public List<Specie> getAllSpecies(){
		System.out.println("Get all species");
		return jdbcSpecieRepository.getAllSpecie();
	}
	

	@GetMapping("/species/{id}")
	public List<Specie> findById(@PathVariable("id") Integer id) {
		System.out.println("ci lele");
		return jdbcSpecieRepository.findSpecieById(id);
	}
	
	@PostMapping("/addSpecie/{common_name}/{scientific_name}")
	public void addSpecie(@PathVariable("common_name") String common_name, @PathVariable("scientific_name") String scientific_name) {
		jdbcSpecieRepository.addSpecie(common_name , scientific_name);
	}
	
	@PutMapping("/updateSpecie/{common_name}/{scientific_name}/{id_specie}")
	public void updateSpecie(@PathVariable("common_name") String common_name, @PathVariable("scientific_name")String scientific_name,@PathVariable("id_specie") int id_specie) {
		jdbcSpecieRepository.updateSpecie(common_name, scientific_name, id_specie);
	}
	
	@DeleteMapping("/deleteSpecie/{id_specie}")
	public void deleteSpecie(@PathVariable("id_specie") int id_specie) {
		System.out.println("Delete");
		jdbcSpecieRepository.deleteSpecie(id_specie);
	}
	
	@RequestMapping("/specie/{common_name}")
	public Specie getSpecieByName(@PathVariable String common_name) {
		Specie specie = jdbcSpecieRepository.findSpecieByName(common_name).get(0);
		return specie;
		
	}

}
