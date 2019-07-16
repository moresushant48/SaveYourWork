package io.moresushant48.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
	
import io.moresushant48.model.User;
import io.moresushant48.services.UserService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private UserService userService;

	/*
	 * Return basic login page to the user.
	 */
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("currentPage","login");
		return mv;
	}
	
	/*
	 * Return the Registration page for new users.
	 */
	@GetMapping("/register")
	public String registerGet(Model model) {
		model.addAttribute("registerDetails", new User());
		model.addAttribute("currentPage","register");
		return "register.html";
	}
	
	/*
	 * Control the entered data, check for username, email existence, and then add the user.
	 */
	@PostMapping("/register")
	public ModelAndView registerPost(@ModelAttribute("registerDetails") User user) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		return mv = userService.registerUser(user,mv);
	}
}