package io.moresushant48.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.User;
import io.moresushant48.services.UserService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	/*
	 * Easy Login Way. Return the login page when requested with proper username.
	 */
	@GetMapping("/u/{username}")
	public ModelAndView redirectLogin(@PathVariable("username") String username) {
		ModelAndView mv = new ModelAndView();
		
		if( userRepository.findByUsername(username) != null )
			mv.setViewName("redirect:/login?username=" + username);
		else
			mv.setViewName("redirect:/"); // return to home if username dosen't exist.
		
		return mv;
	}
	
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
	
	/*
	 * Control the forgotUsername request.
	 */
	
	@GetMapping("/forgotUsername")
	public ModelAndView forgotUsernameGET() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentPage","forgotUsername");
		mv.setViewName("login");
		return mv;
	}
	
	@PostMapping("/forgotUsername")
	public ModelAndView forgotUsernamePOST(@RequestParam("email") String email) {
		
		ModelAndView mv = new ModelAndView();
		mv = userService.forgotUsername(mv,email);
		return mv;
	}
	
	/*
	 * Control the forgotPassword request.
	 */
	
	@GetMapping("/forgotPassword")
	public ModelAndView forgotPasswordGET() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentPage","forgotPassword");
		mv.setViewName("login");
		return mv;
	}
	
	@PostMapping("/forgotPassword")
	public ModelAndView forgotPasswordPOST(@RequestParam("email") String emailstr) {
		
		ModelAndView mv = new ModelAndView();
		mv = userService.forgotPassword(mv,emailstr);
		return mv;
	}
	
	/*
	 *  Reset the passward by email authentication.
	 */
	
	@GetMapping("/newPassword")
	public ModelAndView newPassword(@RequestParam("token") String receivedToken, @RequestParam("email") String encodedEmail) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		
		mv = userService.verifyTokenAndEmail(mv,receivedToken,encodedEmail);
		
		return mv;
	}
	
	@PostMapping("/newPassword")
	public ModelAndView newPasswordPOST(@RequestParam("email") String email, @RequestParam("password") String password) {
		ModelAndView mv = new ModelAndView();
		mv = userService.changePassword(mv, email, password);
		return mv;
	}
	
	@GetMapping("/gotNewPassword")
	public ModelAndView gotNewPassword() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("currentPage","newPassword");
		return mv;
	}
}