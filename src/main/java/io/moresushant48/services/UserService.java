package io.moresushant48.services;

import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.model.User;

public interface UserService {

	public void saveUser(User user);
	public String findByUsername(String username);
	public String findByEmail(String email);
	public User[] listAllUsers();
	public User getUserDetails(int id);
	public void updateRole(int roleID, int userID);
	public ModelAndView registerUser(User user, ModelAndView mv);
}
