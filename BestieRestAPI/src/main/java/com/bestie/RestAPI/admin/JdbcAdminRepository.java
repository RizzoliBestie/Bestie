package com.bestie.RestAPI.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bestie.RestAPI.user.User;

@Repository(value="admin")
public class JdbcAdminRepository implements AdminRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean checkAdmin(String username, String password) {
		if(jdbcTemplate.query("SELECT * FROM User WHERE username = ? AND password = ?",
				BeanPropertyRowMapper.newInstance(User.class),
				username, password).size()==0) {
			return false;
		}
		return true;
	}

}
