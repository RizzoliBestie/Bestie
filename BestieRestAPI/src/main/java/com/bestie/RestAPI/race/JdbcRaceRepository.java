package com.bestie.RestAPI.race;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value="race")
public class JdbcRaceRepository implements RaceRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Race> getAllRaces(){
		return jdbcTemplate.query("SELECT * FROM Race", BeanPropertyRowMapper.newInstance(Race.class));
	}

	
	
}
