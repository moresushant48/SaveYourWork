package io.moresushant48.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.ServiceLoginRepository;
import io.moresushant48.impl.ServiceLogin;

@Controller
public class ServiceLoginController {

	@Autowired
	ServiceLoginRepository serviceLoginRepository;
	
	public static String uname = null;
	
	@GetMapping(path ="/{name}")
	public ModelAndView checkUsername(@PathVariable("name") String username, Model model){
		model.addAttribute("serviceLoginDetails", new ServiceLogin());
	  
	    ModelAndView mv = new ModelAndView();
	    uname = username;
		return serviceLoginRepository.checkForUsername(username,mv);
	}
	
	@PostMapping("/{name}")
	public ModelAndView checkPassword(@ModelAttribute("serviceLoginDetails") ServiceLogin serviceLogin) {
		
		ModelAndView mv = new ModelAndView();
		serviceLoginRepository.checkForPassword(serviceLogin,uname,mv);
		
		return mv;
	}
}
