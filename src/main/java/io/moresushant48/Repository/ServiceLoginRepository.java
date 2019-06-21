package io.moresushant48.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.impl.MD5Generator;
import io.moresushant48.impl.ServiceLogin;

@Repository
public class ServiceLoginRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
		
	public ModelAndView checkForUsername(String username, ModelAndView mv) {
		
		String sql = "SELECT count(*) from users where username = ?";
		Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class, username);
		
		if(cnt == 1) {
			mv.setViewName("serviceLogin.html");
			
		}else if (cnt > 1 || cnt < 1) {
			
			mv.setViewName("noUserFound.html");
		}
		return mv;
	}

	public ModelAndView checkForPassword(ServiceLogin serviceLogin, String username, ModelAndView mv) {
		
		serviceLogin.setPassword(MD5Generator.MD5(serviceLogin.getPassword()));
		
		String sql = "SELECT pswd from users where username = ?";
		
		String pswd = jdbcTemplate.queryForObject(sql, String.class, username);
		
		System.out.println("A : " + serviceLogin.getPassword() + "\nB : " + pswd);

		if(serviceLogin.getPassword().equals(pswd)) {
			mv.setViewName("redirect:/");
		} else {
			mv.setViewName("redirect:/{name}");
			mv.addObject("result", "Password is incorrect.");
		}
		
		return mv;
	}
	
	
}