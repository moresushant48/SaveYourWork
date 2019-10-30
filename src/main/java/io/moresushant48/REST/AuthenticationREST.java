package io.moresushant48.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.Role;
import io.moresushant48.model.User;

@RestController
@RequestMapping("/rest")
public class AuthenticationREST {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/login")
	public Integer login(@RequestParam("username") String username, @RequestParam("password") String password) {
			
		User user = userRepository.findByUsername(username);
		if(user!=null) {
			if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
				return user.getId();
			}
		}
		return -1;
	}
	
	@PostMapping("/register")
	public String register(@RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password) {
		
		User userEmail = userRepository.findByEmail(email);
		if(userEmail != null) {
			
			return email;
		}else{
			
			User userUsername = userRepository.findByUsername(username);
			if(userUsername != null) {
				
				return username;
				
			}else {
				
				User user = new User();
				Role role = new Role();
				role.setId(3);
				
				user.setEmail(email.trim());
				user.setUsername(username.trim());
				user.setPassword(bCryptPasswordEncoder.encode(password.trim()));
				user.setRole(role);
				
				userRepository.save(user);
				return "Registration Successful.";
			}			
		}
	}
	
}
