package io.moresushant48.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private User user = null;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/account")
	public ModelAndView accountGET(Principal principal) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user");
		mv.addObject("currentPage", "account");
		
		user = userRepository.findByUsername(principal.getName().trim());
		mv.addObject("user", user);
		
		return mv;
	}
	
	/*
	 * Reset Password GET Controller
	 */
	@GetMapping("/account/resetPassword")
	public ModelAndView resetPasswordGET() {
		ModelAndView mv = new ModelAndView();
		
		if(user == null) {
			mv.setViewName("redirect:/user/account");	
		}else {
			mv.addObject("email",user.getEmail());
			mv.setViewName("user");
			mv.addObject("currentPage", "resetPassword");
		}
		
		return mv;
	}
	
	/*
	 * Reset Password POST Controller
	 */
	@PostMapping("/account/resetPassword")
	public ModelAndView resetPasswordPOST(@RequestParam("password") String password) {
		ModelAndView mv = new ModelAndView();
		
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		if(userRepository.save(user) != null) {
			mv.setViewName("redirect:/user/account/resetPassword?success=true");
		}else {
			mv.setViewName("redirect:/user/account/resetPassword?failed=true");
		}

		return mv;
	}
	
	
	
}
