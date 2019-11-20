package io.moresushant48.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/account")
	public ModelAndView accountGET(Principal principal) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user");
		mv.addObject("currentPage", "account");
		
		User user = userRepository.findByUsername(principal.getName().trim());
		mv.addObject("user", user);
		
		return mv;
	}
	
}
