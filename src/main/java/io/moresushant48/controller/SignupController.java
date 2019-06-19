package io.moresushant48.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.impl.SignupImpl;

@Controller
public class SignupController {
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("registerDetails", new SignupImpl() );
		return "signup.html";
	}
	
	@PostMapping("/signup")
	public ModelAndView sign_up(@ModelAttribute("registerDetails") SignupImpl signupImpl, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("result","Registered Successfully.");
		return mv;
	}
}