package io.moresushant48.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	@GetMapping("/adminPanel")
	public ModelAndView adminPanel() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adminPanel");
		return mv;
	}
}
