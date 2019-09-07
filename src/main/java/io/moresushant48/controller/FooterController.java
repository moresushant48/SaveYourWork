package io.moresushant48.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.model.Feedback;
import io.moresushant48.services.FeedbackService;

@Controller
public class FooterController {

	@Autowired
	FeedbackService feedbackService;
	
	@GetMapping("/feedback")
	public ModelAndView feedbackGET(Model model) {
		ModelAndView mv = new ModelAndView();
		model.addAttribute("feedback", new Feedback());
		mv.setViewName("feedback");
		mv.addObject("currentPage","feedback");
		return mv;
	}
	
	@PostMapping("/feedback")
	public ModelAndView feedbackPOST(@ModelAttribute("feedback") Feedback feedback) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/feedback");
		feedbackService.saveFeedback(feedback);
		return mv;
	}
}
