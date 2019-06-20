package io.moresushant48.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.SignupRepository;
import io.moresushant48.impl.Signup;

@Controller
public class SignupController{
	
	@Autowired
	SignupRepository signupRepository;
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("registerDetails", new Signup() );
		return "signup.html";
	}
	
	@PostMapping("/signup")
	public ModelAndView sign_up(@ModelAttribute("registerDetails") Signup signup, BindingResult bindingResult) {
		
		String validationResult = signupRepository.addNewUser(signup);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("result",validationResult);
		return mv;
	}
}