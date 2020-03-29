package io.moresushant48.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.FeedbackRepository;
import io.moresushant48.Repository.FileRepository;
import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.AnalyticsData;
import io.moresushant48.model.Feedback;
import io.moresushant48.model.User;
import io.moresushant48.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	FeedbackRepository feedbackRepository;

	@Autowired
	SessionRegistry sessionRegistery;
	
	@GetMapping("/analytics")
	public ModelAndView analytics(Principal principal) {
		ModelAndView mv = new ModelAndView("adminPanel");
		mv.addObject("currentPage", "analytics");
		return mv;
	}
	
	@GetMapping("/analytics/get")
	@ResponseBody
	public AnalyticsData getAnalyticsData() {
		
		return new AnalyticsData(userRepository.count(),
				fileRepository.count(), feedbackRepository.count(), 
				sessionRegistery.getAllPrincipals().stream()
				.filter(u -> !sessionRegistery.getAllSessions(u, false).isEmpty()).count());
	}
	
	@GetMapping("/list-users")
	public ModelAndView listUsers() {
		ModelAndView mv = new ModelAndView("adminPanel");
		mv.addObject("currentPage", "listUsers");
		return mv;
	}
	
	@GetMapping("/list-users/get")
	@ResponseBody
	public User[] getUsers() {
		
		return userService.listAllUsers();
	}
	
	@GetMapping("/delete-user")
	@ResponseBody
	public void deleteUser(@RequestParam("id") int id) {
		
		userRepository.deleteById(id);
	}
	
	@GetMapping("/manage-role")
	public ModelAndView role(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("userDetails");
		mv.addObject("currentPage", "userDetails");
		mv.addObject("user", userService.getUserDetails(id));
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
		ModelAndView mv = new ModelAndView("adminPanel");
		mv.addObject("currentPage", "userFeedback");
		return mv;
	}
	
	@GetMapping("/userFeedback/get")
	@ResponseBody
	public Feedback[] getFeedbacks() {
		
		return feedbackRepository.getFeedbacks();
	}
	
	@GetMapping("/delete-feedback")
	@ResponseBody
	public void deleteFeedback(@RequestParam("id") Long id) {
		
		feedbackRepository.deleteById(id);
	}
}