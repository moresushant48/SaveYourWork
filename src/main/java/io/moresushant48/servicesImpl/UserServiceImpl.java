package io.moresushant48.servicesImpl;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.Email;
import io.moresushant48.model.Role;
import io.moresushant48.model.User;
import io.moresushant48.services.UserService;

/*
 * Service Class that acts as interface betn Repo and Model
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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
		return userRepository.getUserDetails(id);
	}
	
	public void updateRole(int roleID, int userID) {
		userRepository.updateRole(roleID, userID);
	}
	
	/*
	 * Check for Username and Email existence and Add the User accordingly
	 */
	public ModelAndView registerUser(User user, ModelAndView mv) {
		if(findByEmail(user.getEmail()) == null) {
			
			if(findByUsername(user.getUsername()) == null) {
				Role role = new Role();
				role.setId(3);
				user.setRole(role);
				saveUser(user);
//				mv.addObject("/register?success=true");
				mv.setViewName("redirect:/register?success=true");
				return mv;
			}else {
				mv.setViewName("redirect:/register?userexists=true");
				return mv;
			}
		} else {
			mv.setViewName("redirect:/register?emailexists=true");
			return mv;
		}
	}
	
	/*
	 * Check for valid email and mail the username to client.
	 */
	@Override
	public ModelAndView forgotUsername(ModelAndView mv, String email) {
        
		User user = findByEmail(email);
		
		if(user != null) {
			
			MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);
	        try {
	            helper.setFrom(Email.getMyEmail());
	            helper.setTo(email);
	            helper.setSubject("Username at SaveYourWork");
	            helper.setText("Your Credentials at SaveYourWork are : \nUsername : " + user.getUsername() 
	            		+ "\n\nIf this wasn't you, make use of our contact service : http://localhost:8888/contactus"
        				+ "\n\nRegards,\nSushant More @ SaveYourWork.");
	            
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        javaMailSender.send(message);
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
			
			MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);
	        try {
	        		        	
	            helper.setFrom(Email.getMyEmail());
	            helper.setTo(email);
	            helper.setSubject("Password at SaveYourWork");
	            helper.setText("You have requested to change the Password for your SaveYourWork account. Click on the link below "
	            		+ "and fill your new password : \n\n" + "http://localhost:8888/newPassword?token=" + generatedToken
	            		+ "&email=" + this.encEmail + "\n\nIf this wasn't you, make use of our contact service : http://localhost:8888/contactus"
	            				+ "\n\nRegards,\nSushant More @ SaveYourWork.");
	            System.out.println(generatedToken);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        javaMailSender.send(message);
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
}
