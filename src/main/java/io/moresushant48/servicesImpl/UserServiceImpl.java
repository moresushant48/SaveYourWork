package io.moresushant48.servicesImpl;

import java.security.SecureRandom;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.Role;
import io.moresushant48.model.User;
import io.moresushant48.services.EmailService;
import io.moresushant48.services.UserService;

/*
 * Service Class that acts as interface betn Repo and Model
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private String generatedToken;
	private String encEmail;
	private String email;
	
	// constructor
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// default JpaRepo's save(entity) call
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	// Return Existence of Username as a result
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	// Return Existence of email as a result
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	// Return User object array flooded with List of all users.
	public User[] listAllUsers() {
		return userRepository.listAllUsers();
	}
	
	// Return User object filled with User detail of respective id.
	public User getUserDetails(int id) {
		return userRepository.getOne(id);
	}
	
	public void updateRole(int roleID, int userID) {
		userRepository.updateRole(roleID, userID);
	}
	
	/*
	 * Check for Username and Email existence and Add the User accordingly
	 */
	public String registerUser(User user) {
		if(findByEmail(user.getEmail()) == null) {
			
			if(findByUsername(user.getUsername()) == null) {
				Role role = new Role();
				role.setId(3);
				user.setRole(role);
				user.setPublicPass(String.valueOf(new SecureRandom().nextInt(999999)));
				saveUser(user);
				
				return "success";
			}else {
				
				return "username";
			}
		} else {
			
			return "email"; 
		}
	}
	
	/*
	 * Check for valid email and mail the username to client.
	 */
	@Override
	public ModelAndView forgotUsername(ModelAndView mv, String email) {
        
		User user = findByEmail(email);
		
		if(user != null) {
			
			emailService.sendEmail(email, "Username at SaveYourWork", "Your Credentials at SaveYourWork are : \nUsername : " + user.getUsername() 
    					+ "\n\nIf this wasn't you, make use of our contact service : http://localhost:8888/contactus"
    					+ "\n\nRegards,\nSushant More @ SaveYourWork.");
			
	        mv.setViewName("redirect:/forgotUsername?found=true");
		}
		else {
			mv.setViewName("redirect:/forgotUsername?notfound=true");
		}
		
		return mv;
	}	
	
	/*
	 * Check for valid email and process to change the user password.
	 */
	@Override
	public ModelAndView forgotPassword(ModelAndView mv, String emailstr) {
		
		User user = findByEmail(email);
		generatedToken = UUID.randomUUID().toString();
		this.email = emailstr;
		this.encEmail = bCryptPasswordEncoder.encode(emailstr);
		
		if(user != null) {
			
			emailService.sendEmail(email,"Password at SaveYourWork", "You have requested to change the Password for your SaveYourWork account. Click on the link below "
            		+ "and fill your new password : \n\n" + "http://localhost:8888/newPassword?token=" + generatedToken
            		+ "&email=" + this.encEmail + "\n\nIf this wasn't you, make use of our contact service : http://localhost:8888/contactus"
            				+ "\n\nRegards,\nSushant More @ SaveYourWork.");
			
	        mv.setViewName("redirect:/forgotPassword?found=true");
		}
		else {
			mv.setViewName("redirect:/forgotPassword?notfound=true");
		}
		
		return mv;
	}

	/*
	 * Check if the ReceivedToken and the Received EncodedEmail is matching.
	 */
	
	@Override
	public ModelAndView verifyTokenAndEmail(ModelAndView mv, String receivedToken, String encodedEmail) {
		
		if(this.generatedToken.equals(receivedToken) && this.encEmail.equals(encodedEmail)) {
			mv.addObject("userEmail", this.email);
			mv.addObject("currentPage","newPassword");
		}else {
			mv.setViewName("login");
		}
		return mv;
	}
	
	/*
	 * Take the email and password parameters and update the password for defined email.
	 */

	@Override
	public ModelAndView changePassword(ModelAndView mv, String email, String password) {
		
		User user = findByEmail(email);
		if(user != null) {
			user.setPassword(password);
			saveUser(user);
			mv.setViewName("redirect:/gotNewPassword?success=true");
		}
		else {
			mv.setViewName("redirect:/gotNewPassword?unsuccess=true");
		}
		
		
		return mv;
	}

	@Override
	public Boolean resetPassword(int id, String password) {
		
		User user = userRepository.getOne(id);
		if(user != null) {
			user.setPassword(new BCryptPasswordEncoder().encode(password));
			if(userRepository.save(user) != null) {
				return true;
			}
		}
		return false;
	}	
}
