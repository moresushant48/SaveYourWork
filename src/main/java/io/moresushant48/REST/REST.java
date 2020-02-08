package io.moresushant48.REST;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public User viewList(@PathVariable("id") int id) {
		
		User user = userRepository.findById(id).get();
		user.setPassword("null");
		

		return user;
	}
}
