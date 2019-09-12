package io.moresushant48.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.moresushant48.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long>{
	
	@Query(value = "select * from user_files", nativeQuery = true)
	public File[] listFiles();
}
