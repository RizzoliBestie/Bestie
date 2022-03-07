package com.bestie.RestAPI.specie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value="specie")
public class JdbcSpecieRepository implements SpecieRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Specie> getAllSpecie() {
		return jdbcTemplate.query("SELECT * FROM Specie", BeanPropertyRowMapper.newInstance(Specie.class));
	}
	
}
