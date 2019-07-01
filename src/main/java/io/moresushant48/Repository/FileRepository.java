package io.moresushant48.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import io.moresushant48.model.File;

@Repository
public interface FileRepository extends CrudRepository<File, String>{
	
	@Query(value = "select * from user_files", nativeQuery = true)
	public File[] listFiles();
}