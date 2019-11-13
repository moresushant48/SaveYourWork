package io.moresushant48.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.moresushant48.Repository.FileRepository;
import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.File;
import io.moresushant48.model.User;
import io.moresushant48.storage.StorageService;

@RestController
@RequestMapping("/rest")
public class FileREST {
	
	private final StorageService storageService;
    private User user;
    private File[] myFiles;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
    public FileREST(StorageService storageService) {
        this.storageService = storageService;
    }
	
	@GetMapping("/list-files/{id}")
	public File[] listAllFiles(@PathVariable("id") int id) {
		
		File[] files = fileRepository.listFiles(id);
		return files;
	}
	
	@PostMapping("/upload-file/{id}")
	public boolean handleFileUpload(@PathVariable("id") int id, @RequestPart("file") MultipartFile filename) {
		
		user = userRepository.getOne(id);
		myFiles = fileRepository.listFiles(id);
		storageService.store(filename, user, myFiles);
	
		return true;
	}
	
	@GetMapping("/delete-file/{fileId}")
	public boolean deleteFile(@PathVariable("fileId") Long fileId, @RequestParam("fileName") String fileName ) {
		
		storageService.deleteById(fileId, fileName);
		return true;
	}

}
