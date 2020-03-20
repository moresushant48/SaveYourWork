package io.moresushant48.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.moresushant48.Repository.FileRepository;
import io.moresushant48.model.Access;
import io.moresushant48.model.File;

@Controller
@RequestMapping("/list-files")
public class AccessController {

	private File file;
	private Access access = new Access();
	
	@Autowired
	private FileRepository fileRepository;
	
	@GetMapping("/public")
	@ResponseBody
	public void accessPublic(@RequestParam("fileId") Long id) {
		
		file = fileRepository.getOne(id);
		access.setId(3);
		file.setAccess(access);
		fileRepository.save(file);
		
	}
	
	@GetMapping("/protected")
	@ResponseBody
	public void accessProtected(@RequestParam("fileId") Long id) {
		
		file = fileRepository.getOne(id);
		access.setId(2);
		file.setAccess(access);
		fileRepository.save(file);

	}
	
	@GetMapping("/private")
	@ResponseBody
	public void accessPrivate(@RequestParam("fileId") Long id) {
		
		file = fileRepository.getOne(id);
		access.setId(1);
		file.setAccess(access);
		fileRepository.save(file);

	}	
}
