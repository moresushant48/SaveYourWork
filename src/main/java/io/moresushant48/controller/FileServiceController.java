package io.moresushant48.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileServiceController {

	@GetMapping("/fileService")
	public String fileServiceGET(@ModelAttribute("username") String username) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("username", username);
		return "fileService.html";
	}
}
