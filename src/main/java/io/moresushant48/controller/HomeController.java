package io.moresushant48.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/{username}")
    public ModelAndView fileAccessChooser(@PathVariable("username") String username ) {
    	
    	ModelAndView mv = new ModelAndView();
    	User user = userRepository.findIdByUsername(username);
    	
    	if(user != null) {
			mv.addObject("currentPage", "fileAccess");
			mv.setViewName("home");
		}else {
			mv.setViewName("redirect:/");
		}
    	return mv;
    }
	
	@GetMapping("/{username}/public")
	public ModelAndView publicFiles(@PathVariable("username") String username) {
		
		ModelAndView mv = new ModelAndView();
    	User user = userRepository.findIdByUsername(username);
    	
    	if(user != null) {
			mv.addObject("files", fileRepository.listPublicFiles(user.getId()));
			mv.addObject("currentPage", "fileAccessPublic");
			mv.setViewName("home");
		}else {
			mv.setViewName("redirect:/");
		}
    	return mv;
	} 
	
	@GetMapping("/{username}/protected/login")
	public ModelAndView protectedFilesLoginGET(@PathVariable("username") String username) {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("currentPage", "askTempPass");
		return mv;
	}
	
	@PostMapping("/{username}/protected/login")
	public ModelAndView protectedFilesLoginPOST(@PathVariable("username") String username, @RequestParam("password") String password) {
		ModelAndView mv = new ModelAndView("home");
		User user = userRepository.findIdByUsername(username);
		
		if( password.equals(user.getPublicPass())) {
			mv.addObject("files", fileRepository.listProtectedFiles(user.getId()));
			mv.addObject("currentPage", "fileAccessProtected");
			return mv;
		}else {
			mv.addObject("currentPage", "askTempPass");
			mv.setViewName("redirect:/" + username + "/protected/login?error=true");
			return mv;
		}
	}
}