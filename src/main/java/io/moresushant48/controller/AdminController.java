package io.moresushant48.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.FeedbackRepository;
import io.moresushant48.Repository.UserRepository;
import io.moresushant48.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FeedbackRepository feedbackRepository;

	@GetMapping("/list-users")
	public ModelAndView listUsers() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentPage", "adminPanel");
		mv.addObject("users", userService.listAllUsers());
		mv.setViewName("adminPanel");
		return mv;
	}
	
	@GetMapping("/delete-user")
	public ModelAndView deleteUser(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView();
		userRepository.deleteById(id);
		mv.setViewName("redirect:/admin/list-users");
		return mv;
	}
	
	@GetMapping("/manage-role")
	public ModelAndView role(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentPage", "userDetails");
		mv.addObject("user", userService.getUserDetails(id));
		mv.setViewName("userDetails");
		return mv;
	}
	
	@PostMapping("/updateRole")
	public ModelAndView updateRole(@RequestParam("role") int roleID, @RequestParam("id") int userID) {
		ModelAndView mv = new ModelAndView();
		userService.updateRole(roleID, userID);
		mv.setViewName("redirect:/admin/list-users");
		return mv;
	}
	
	@GetMapping("/userFeedback")
	public ModelAndView userFeedback() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("feedbacks",feedbackRepository.getFeedbacks());
		mv.addObject("currentPage", "userFeedback");
		mv.setViewName("adminPanel");
		return mv;
	}
	
	@GetMapping("/delete-feedback")
	public ModelAndView deleteFeedback(@RequestParam("id") Long id) {
		ModelAndView mv = new ModelAndView();
		feedbackRepository.deleteById(id);
		mv.setViewName("redirect:/admin/userFeedback");
		return mv;
	}
}