package io.moresushant48.REST;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.User;
import io.moresushant48.services.UserService;

@RestController
@RequestMapping("/rest/user")
public class UserREST {

	private User user;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getUserId")
	public Integer getUserId(@RequestParam("username") String username) {
		
		user = userRepository.findByUsername(username);
		if(user != null)
			return user.getId();
		else
			return 0;
	}
	
	@GetMapping("/account/getKey/{userId}")
	public String getKey(@PathVariable("userId") int userId) {
		
		return userRepository.getOne(userId).getPublicPass();
	}
	
	@GetMapping("/account/genKey/{userId}")
	public String genKey(@PathVariable("userId") int userId) {
		
		User user = userRepository.getOne(userId);
		String newSharedKey = String.valueOf(new SecureRandom().nextInt(999999));
		user.setPublicPass(newSharedKey);
		userRepository.save(user);
		
		return newSharedKey;
	}	
	
	@PostMapping("/resetPassword/{userId}")
	public Boolean resetPassword(@PathVariable("userId") int userId, @RequestParam("password") String password) {

		return userService.resetPassword(userId, password);
	}
	
}
