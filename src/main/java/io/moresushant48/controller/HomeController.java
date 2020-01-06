package io.moresushant48.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.FileRepository;
import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.User;

@Controller
public class HomeController {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	FileRepository fileRepository;
	
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
	
	 @GetMapping("/{pubUsername}")
	    public ModelAndView publicFiles(@PathVariable("pubUsername") String pubUsername ) {
	    	
	    	ModelAndView mv = new ModelAndView();
	    	User user = userRepository.findIdByUsername(pubUsername);
	    	
	    	if(user != null) {
				mv.addObject("files", fileRepository.listPublicFiles(user.getId()));
				mv.addObject("currentPage", "publicFiles");
				mv.setViewName("home");
			}else {
				mv.setViewName("redirect:/");
			}
	    	return mv;
	    }
}