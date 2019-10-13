package io.moresushant48.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.Role;
import io.moresushant48.model.User;

@RestController
@RequestMapping("/rest")
public class REST {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/{id}")
	@JsonIgnore
	public Optional<User> viewList(@PathVariable("id") int id) {
		
		return userRepository.findById(id);
	}
	
	@RequestMapping("/success")
	public String success() {
		
		return "Login Successful.";
	}
	
	@RequestMapping("/failed")
	public String failed() {
		
		return "Login Failed.";
	}
	
	
	@PostMapping("/login")
	public User login(@RequestParam("username") String username) {
		
		User user = userRepository.findByUsername(username);
		
		if(user != null) {
			return user;
		}
		return user;
	}
	
	@PostMapping("/register")
	public String register(@RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password) {
		
		User userEmail = userRepository.findByEmail(email);
		if(userEmail != null) {
			
			return "Email Exists.";
		}else{
			
			User userUsername = userRepository.findByUsername(username);
			if(userUsername != null) {
				
				return "Username Exists.";
			}else {
				
				User user = new User();
				
				Role role = new Role();
				role.setId(3);
				
				user.setEmail(email.trim());
				user.setUsername(username.trim());
				user.setPassword(bCryptPasswordEncoder.encode(password.trim()));
				user.setRole(role);
				
				userRepository.save(user);
				return "Register Success.	";
			}			
		}
	}
}
