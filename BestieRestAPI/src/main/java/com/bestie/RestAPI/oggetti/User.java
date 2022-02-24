package com.bestie.RestAPI.oggetti;

public class User {

	private int id_user;
	private String username;
	private String email;
	private String password;
	private String phone_number;
	
	public User() {
		this.id_user = 0;
		this.username = "";
		this.email = "";
		this.password = "";
		this.phone_number = "";
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	
	
	
	
}
