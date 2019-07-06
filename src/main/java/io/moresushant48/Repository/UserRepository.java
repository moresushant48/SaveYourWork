package io.moresushant48.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.moresushant48.model.User;

/*
 * Extended with CRUD Repo to get some default DB connectivity functions
 */
public interface UserRepository extends CrudRepository<User, Integer>{

	@Query(value = "SELECT username FROM auth_user where username = ?1", nativeQuery = true)
	public String findByUsername(String username);
	
	@Query(value = "SELECT email FROM auth_user where email = ?1", nativeQuery = true)
	public String findByEmail(String email);
	
	@Query(value = "SELECT * FROM auth_user", nativeQuery = true)
	public User[] listAllUsers();
}