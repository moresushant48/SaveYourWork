package io.moresushant48.controller;

import java.io.IOException;
import java.security.Principal;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.FileRepository;
import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.User;
import io.moresushant48.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private User user = null;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/list-files")
	public ModelAndView listUploadedFiles(Model model, Principal principal) throws IOException {	
		ModelAndView mv = new ModelAndView();
		
		user = userRepository.findByUsername(principal.getName());
		model.addAttribute("uid",user.getId());
		mv.addObject("currentPage", "home");
		mv.setViewName("home");
		return mv;
	}

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
		
		if(userService.resetPassword(user.getId(), password)) {
			mv.setViewName("redirect:/user/account/resetPassword?success=true");
		}else {
			mv.setViewName("redirect:/user/account/resetPassword?failed=true");
		}

		return mv;
	}
	
	/*
	 * Generate new Security Key (used for protected file access) for user acc. 
	 */
	
	@GetMapping("/account/genKey")
	@ResponseBody
	public String genKey() {
		
		user.setPublicPass(String.valueOf(new SecureRandom().nextInt(999999)));
		userRepository.save(user);
		
		return user.getPublicPass();
	}
	
	/*
	 * Delete the user account and all their data.
	 */
	
	@GetMapping("/account/delete/{uid}")
	@ResponseBody
	public boolean deleteUserAccount(@PathVariable("uid") int uid) {
		
		if(userRepository.existsById(uid)) {
			userRepository.deleteById(uid);
			fileRepository.deleteByUserId(uid);
		}
		
		
		return true;
	}
}
