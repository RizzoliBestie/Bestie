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

	@Override
	public List<Race> getRaceByName(String name) {
		return jdbcTemplate.query("SELECT * FROM Race WHERE name=? ", BeanPropertyRowMapper.newInstance(Race.class), name);
	}

	@Override
	public List<Race> getRaceBySpecie(int id_specie) {
		return jdbcTemplate.query("SELECT * FROM Race WHERE id_specie = ? ", BeanPropertyRowMapper.newInstance(Race.class), id_specie);	
	}

	@Override
	public List<Race> getRaceById(int id_race) {
		return jdbcTemplate.query("SELECT * FROM Race WHERE id_race=? ", BeanPropertyRowMapper.newInstance(Race.class), id_race);
	}

	@Override
	public void deleteRace(int id_race) {
		jdbcTemplate.update("DELETE FROM Race WHERE id_race = ? ", id_race);
		
	}

	@Override
	public void updateRace(String name, String information, String size, int id_race) {
		jdbcTemplate.update("UPDATE Race SET name = ? , information = ? , size = ? WHERE id_race = ?", name, information, size, id_race);
	}

	

	
	
	
}
