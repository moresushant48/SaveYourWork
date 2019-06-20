package io.moresushant48.Repository;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import io.moresushant48.impl.MD5Generator;
import io.moresushant48.impl.Signup;

@Repository
public class SignupRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/*
	 * Check if there exists a same username as the entered value.
	 */
	public int validateUsername(Signup signup) {
		
		Integer cnt = jdbcTemplate.queryForObject(
			    "SELECT count(*) FROM users WHERE username = ?", Integer.class, signup.getUsername());
		 
		return cnt;
	}
	
	/*
	 * Check if there exists an account with same email address.
	 */
	public int validateEmail(Signup signup) {
		
		Integer cnt = jdbcTemplate.queryForObject(
			    "SELECT count(*) FROM users WHERE email = ?", Integer.class, signup.getMail());
		
		return cnt;
	}
	
	/*
	 * Implements validateUsername() and validateEmail() and creates account if everything goes right.
	 */
	public String addNewUser(Signup signup) throws UnsupportedEncodingException {
		
		String validationResult = null;
		
		if(validateUsername(signup) > 0) {
			
			validationResult = "Username already exists, use another.";
			
		}else if(validateEmail(signup) > 0) {
			
			validationResult = "Account exists already for this email id.";
			
		}else {
		
			String sql = "INSERT INTO users(username,email,pswd) values(?,?,?)";
			
			signup.setPassword(MD5Generator.MD5(signup.getPassword()));
			
			jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
	
				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setString(1, signup.getUsername());
					ps.setString(2, signup.getMail());
					ps.setString(3, signup.getPassword());
					return ps.execute();
				}
			});
			validationResult = "Registered Successfully, please Login.";
		}
		return validationResult;
	}
	
}
