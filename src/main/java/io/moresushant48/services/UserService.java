package io.moresushant48.services;

import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.model.User;

public interface UserService {

	public void saveUser(User user);
	public User findByUsername(String username);
	public User findByEmail(String email);
	public User[] listAllUsers();
	public User getUserDetails(int id);
	public void updateRole(int roleID, int userID);
	public String registerUser(User user);
	public ModelAndView forgotUsername(ModelAndView mv, String email);
	public ModelAndView forgotPassword(ModelAndView mv, String email);
	public ModelAndView changePassword(ModelAndView mv, String email, String password);
	public ModelAndView verifyTokenAndEmail(ModelAndView mv, String receivedToken, String encodedEmail);
	public Boolean resetPassword(int id, String password);
	
}
