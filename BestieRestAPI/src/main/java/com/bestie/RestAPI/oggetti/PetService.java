package com.bestie.RestAPI.oggetti;

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
	
	public List<Pet> getPetById(long id) {
		return jdbcTemplate.query("SELECT * FROM Pet WHERE id_pet=?", BeanPropertyRowMapper.newInstance(Pet.class), id);
	}
}
