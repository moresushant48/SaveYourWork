package io.moresushant48.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.User;


/*
 * Service Class that acts as interface betn Repo and Model
 */
@Service
@Transactional
public class UserService {
	
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// constructor
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// default CRUD's save(entity) call
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	// Return Existence of Username as a result
	public String findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	// Return Existence of email as a result
	public String findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User[] listAllUsers() {
		return userRepository.listAllUsers();
	}
	
	/*
	 * Check for Username and Email existence and Add the User accordingly
	 */
	public ModelAndView registerUser(User user, ModelAndView mv) {
		if(findByEmail(user.getEmail()) == null) {
			
			if(findByUsername(user.getUsername()) == null) {
				
				saveUser(user);
				mv.addObject("result", "Registration Successful.");
				return mv;
			}else {				
				mv.addObject("result", "Username already exists. Choose another !");
				return mv;
			}
		} else {
			mv.addObject("result", "Account already exists for your E-mail!");
			return mv;
		}
	}
}
