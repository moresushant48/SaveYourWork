package io.moresushant48.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import io.moresushant48.model.User;

/*
 * Extended with CRUD Repo to get some default DB connectivity functions
 */
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = "SELECT * FROM auth_user WHERE username = ?1", nativeQuery = true)
	public User findByUsername(String username);
	
	@Query(value = "SELECT * FROM auth_user WHERE username = ?1 AND password = ?2", nativeQuery = true)
	public User findByUsernameAndPassword(String username, String password);
	
	@Query(value = "SELECT * FROM auth_user WHERE email = ?1", nativeQuery = true)
	public User findByEmail(String email);
	
	@Query(value = "SELECT * FROM auth_user WHERE username = ?1", nativeQuery = true)
	public User findIdByUsername(String username);
		
	@Query(value = "SELECT * FROM auth_user", nativeQuery = true)
	public User[] listAllUsers();
	
	@Query(value = "SELECT * FROM auth_user WHERE auth_user_id = ?1", nativeQuery = true)
	public User getUserDetails(int id);
	
	@Modifying
	@Query(value = "UPDATE auth_user SET auth_role_id = ?1 where auth_user_id = ?2", nativeQuery = true)
	public void updateRole(int roleID, int userID);
}