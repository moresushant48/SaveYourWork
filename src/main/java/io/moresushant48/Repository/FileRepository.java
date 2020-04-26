package io.moresushant48.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.moresushant48.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long>{
	
	@Query(value = "SELECT * FROM user_files WHERE auth_user_id = ?1 ORDER BY file_name", nativeQuery = true)
	public ArrayList<File> listFiles(int id);
	
	@Query(value = "SELECT * FROM user_files WHERE auth_user_id = ?1 && access_id = 3 ORDER BY file_name", nativeQuery = true)
	public ArrayList<File> listPublicFiles(int id);

	@Query(value = "SELECT * FROM user_files WHERE auth_user_id = ?1 && access_id = 2 ORDER BY file_name", nativeQuery = true)
	public ArrayList<File> listProtectedFiles(int id);
	
	public void deleteByUserId(int uid);
}
