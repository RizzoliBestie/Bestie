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

	@Override
	public void addSpecie(String common_name, String scientific_name) {
		jdbcTemplate.update("INSERT INTO Specie(common_name,scientific_name) VALUES (? , ? )", common_name, scientific_name);
	}

	@Override
	public void updateSpecie(String common_name, String scientific_name, int id_specie) {
		jdbcTemplate.update("UPDATE Specie SET common_name = ?, scientific_name = ? WHERE id_specie = ?", common_name, scientific_name, id_specie);
	}

	@Override
	public void deleteSpecie(int id_specie) {
		// TODO Auto-generated method stub
		
	}
	
}
