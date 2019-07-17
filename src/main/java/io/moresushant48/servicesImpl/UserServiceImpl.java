package io.moresushant48.servicesImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.UserRepository;
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
	private final UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
	public String findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	// Return Existence of email as a result
	public String findByEmail(String email) {
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
				mv.addObject("result", "Registration Successful.");
				return mv;
			}else {
				mv.addObject("result", "Username already exists. Choose another !");
				return mv;
			}
		} else {
			mv.addObject("result", "Account already exists for your E-mail!");
			return mv;
		}
	}
}
