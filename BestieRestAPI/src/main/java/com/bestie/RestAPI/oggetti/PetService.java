package com.bestie.RestAPI.oggetti;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class PetService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Pet> getAllPets(){
		return jdbcTemplate.query("SELECT * FROM Pet", BeanPropertyRowMapper.newInstance(Pet.class));
	}
	
	public List<Pet> findPetById(long id) {
		return jdbcTemplate.query("SELECT * FROM Pet WHERE id_pet=?", BeanPropertyRowMapper.newInstance(Pet.class), id);
	}
	
	public boolean newPet(Pet pet) {
		final int id_user = pet.getId_user();
		final int race = pet.getId_race();
		final String name = pet.getName();
		final double weight = pet.getWeight();
		final boolean gender = pet.getGender();
		final boolean sterilized = pet.getSterilized();
		final String fur_type = pet.getFur_type();
		//final String uri_image = pet.getUri_image();
		
		jdbcTemplate.update("INSERT INTO Pet(id_user, id_race, name, weight, gender, sterilized, fur_type) VALUES( ? , ? , ? , ? , ?, ?, ? )"
				, id_user, race, name, weight, gender, sterilized, fur_type);
		return true;
	}
	
	public void updatePet(Pet pet) {
		final int id_pet=pet.getId_pet();
		final int id_race = pet.getId_race();
		final String name = pet.getName();
		final double weight = pet.getWeight();
		final boolean gender = pet.getGender();
		final Date birthdate = pet.getBirthDate();
		final Date lastMeal = pet.getLastMeal();
		final Date lastWalk = pet.getLastWalk();
		final boolean sterilized = pet.getSterilized();
		final String fur_type = pet.getFur_type();
		
		jdbcTemplate.update("UPDATE Pet SET id_race = ?, name = ?, weight = ?, gender = ?, birthdate = ?, lastMeal = ?, lastWalk = ?,"
				+ " sterilized = ?, fur_type = ? WHERE id_pet = ?",
				 id_race, name, weight, gender, birthdate, lastMeal, lastWalk, sterilized, fur_type, id_pet);
	}
	
}
