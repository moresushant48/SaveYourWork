package io.moresushant48.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	/*
	 * Return index.html
	 */
	@RequestMapping("/")
	public ModelAndView root() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("currentPage","index");
		return mv; 
	}
}