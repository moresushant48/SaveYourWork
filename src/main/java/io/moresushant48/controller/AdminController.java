package io.moresushant48.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.services.UserService;

@Controller
public class AdminController {
	
	@Autowired
	UserService userService;

	@GetMapping("/adminPanel")
	public ModelAndView adminPanel() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adminPanel");
		return mv;
	}
	
	@GetMapping("/list-users")
	public ModelAndView users() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("users", userService.listAllUsers());
		mv.setViewName("/adminPanel");
		return mv;
	}
	
	@GetMapping("/manage-roles")
	public ModelAndView roles() {
		
		return null;
	}
}
