package com.bestie.RestAPI.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository(value="user")
public class JdbcUserRepository implements UserRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	//Metodi per trovare un utente
	
	@Override
	public List<User> getAllUsers(){
		return jdbcTemplate.query("SELECT * FROM User", BeanPropertyRowMapper.newInstance(User.class));
	}
	
	@Override
	public List<User> findUserById(Integer id) {
		return jdbcTemplate.query("SELECT * FROM User WHERE id_user = ?", BeanPropertyRowMapper.newInstance(User.class) ,id);
		
	}

	@Override
	public List<User> findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM User WHERE username = ?", BeanPropertyRowMapper.newInstance(User.class) ,username);
	}

	@Override
	public List<User> findUserByMail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	//Metodi di autenticazione
	
	@Override
	public List<User> checkLogin(String username, String password) {

		System.out.println(username);
		System.out.println(password);
		return jdbcTemplate.query("SELECT * FROM User WHERE username = ? && password = ?",
				BeanPropertyRowMapper.newInstance(User.class),username,password);
		
	}
	
	@Override
	public List<User> checkSignin(String username, String email) {

		//System.out.println(username);
		//System.out.println(password);
		return jdbcTemplate.query("SELECT * FROM User WHERE username = ? || email = ?",
				BeanPropertyRowMapper.newInstance(User.class),username,email);
		
	}

	@Override
	public boolean register(String username, String email, String password, String phone_number) {
		jdbcTemplate.update("INSERT INTO User(username, email, password, phone_number) VALUES( ? , ? , ? , ? )"
				, username,email,password,phone_number);
		if(checkSignin(username,email).size()==0) {
			return false;
		}else {
			return true;
		}
		
	}

	@Override
	public void updateUser(String username, String email, String phone_number, int id_user) {
		jdbcTemplate.update("UPDATE User SET username = ?, email = ?, phone_number = ? WHERE id_user = ?",
				username, email, phone_number, id_user);
	}
	
	@Override
	public void deleteUser(int id) {
		jdbcTemplate.update("DELETE FROM User WHERE id_user = ?", id);
	}

	
}
