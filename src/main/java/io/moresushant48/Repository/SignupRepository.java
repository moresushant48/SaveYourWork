package io.moresushant48.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import io.moresushant48.impl.Signup;

@Repository
public class SignupRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void addNewUser(Signup signup) {
		
		String sql = "INSERT INTO users(username,email,pswd) values(?,?,?)";
		
		System.out.println(signup.getUsername() + " : " + signup.getMail());
		
		jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, signup.getUsername());
				ps.setString(2, signup.getMail());
				ps.setString(3, signup.getPassword());
				return ps.execute();
			}
		});
	}
	
}
