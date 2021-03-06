package com.bestie.RestAPI.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserRepository repo;

	@GetMapping("/list/users")
	public List<User> getUserList(){
		return repo.getAllUsers();
	}
	
	@GetMapping("/login/{username}/{password}")
	public User getUserLogin(@PathVariable String username, @PathVariable String password) {
		if(username!=null && password!=null) {
			User user = repo.checkLogin(username, password).get(0);
			return user;
		}
		return null;
	}

	@GetMapping("/signin/{username}/{email}")
	public boolean getUserSignin(@PathVariable String username, @PathVariable String email) {
		if(repo.checkSignin(username, email).size()==0)
			return false;
		else
			return true;
	}

	@PostMapping("/signin/{username}/{email}/{password}/{phone_number}")
	public boolean newUser(@PathVariable("username") String username,@PathVariable("email") String email,@PathVariable("password") String password,@PathVariable("phone_number") String phone_number) {
		return repo.register(username, email, password, phone_number);
	}

	@GetMapping("/users/{id}")
	public List<User> findById(@PathVariable("id") Integer id) {
		System.out.println("ci siamooooo");
		return repo.findUserById(id);
	}
	
	@PutMapping("/updateUser/{username}/{email}/{phone_number}/{id_user}")
	public void updateUser(@PathVariable("username") String username, @PathVariable("email") String email, @PathVariable("phone_number") String phone_number, @PathVariable("id_user") int id_user){
		repo.updateUser(username, email, phone_number, id_user);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		repo.deleteUser(id);
	}
	
}
