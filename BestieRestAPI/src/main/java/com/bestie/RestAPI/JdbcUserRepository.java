package com.bestie.RestAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository(value="MySQL")
public class JdbcUserRepository implements UserRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	//Metodi per trovare un utente
	
	@Override
	public List<User> findById(Integer id) {
		return jdbcTemplate.query("SELECT * FROM User WHERE id_user = ?", BeanPropertyRowMapper.newInstance(User.class) ,id);
		
	}

	@Override
	public List<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM User WHERE username = ?", BeanPropertyRowMapper.newInstance(User.class) ,username);
	}

	@Override
	public List<User> findByMail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	//Metodi di autenticazione
	
	@Override
	public List<User> checkLogin(String username, String password) {

		return jdbcTemplate.query("SELECT * FROM User WHERE username = ? && password = ?", BeanPropertyRowMapper.newInstance(User.class), username, password);
		
	}

}
