package io.moresushant48.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.LoginRepository;
import io.moresushant48.impl.Login;

@Controller
public class LoginController {

	@Autowired
	LoginRepository loginRepository;
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginDetails", new Login());
		return "login.html";
	}
	
	@PostMapping("/login")
	public ModelAndView loginPOST(@ModelAttribute("loginDetails") Login login) {
		
		int cnt = loginRepository.checkForCredentials(login);
		ModelAndView mv = new ModelAndView();
		
		return loginRepository.checkForSuccess(mv, cnt, login);
	}
}