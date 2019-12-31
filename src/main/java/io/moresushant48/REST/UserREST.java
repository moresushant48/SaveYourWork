package io.moresushant48.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.moresushant48.Repository.UserRepository;
import io.moresushant48.services.UserService;

@RestController
@RequestMapping("/rest/user")
public class UserREST {

	@Autowired
	UserRepository UserRepository;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/resetPassword/{userId}")
	public Boolean resetPassword(@PathVariable("userId") int userId, @RequestParam("password") String password) {

		return userService.resetPassword(userId, password);
	}
	
}
