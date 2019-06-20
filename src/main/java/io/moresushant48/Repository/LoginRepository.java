package io.moresushant48.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.moresushant48.impl.Login;
import io.moresushant48.impl.MD5Generator;

@Repository
public class LoginRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public String loginUser(Login login) {
		
		String validateLogin = null;
		
		login.setPassword(MD5Generator.MD5(login.getPassword()));
		
		String sql = "SELECT count(*) from users where username = ? and pswd = ?";
		Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class, login.getUsername(), login.getPassword());
				
		System.out.println(cnt);
		
		if(cnt == 1) {
			validateLogin = "Logged In";
		}else if (cnt > 1 || cnt < 1) {
			validateLogin = "Username or Password is incorrect.";
		}
		return validateLogin;
	}
}
