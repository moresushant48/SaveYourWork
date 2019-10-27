package io.moresushant48.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.moresushant48.Repository.FileRepository;
import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.File;

@RestController
@RequestMapping("/rest")
public class FileREST {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FileRepository fileRepository;
	
	@GetMapping("/list-files/{id}")
	public File[] listAllFiles(@PathVariable("id") int id) {
		
		File[] files = fileRepository.listFiles(id);
		return files;
	} 

}
