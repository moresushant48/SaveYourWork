package io.moresushant48.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.Repository.FileRepository;
import io.moresushant48.Repository.UserRepository;
import io.moresushant48.model.File;
import io.moresushant48.model.User;
import io.moresushant48.storage.FileSystemStorageService;
import io.moresushant48.storage.StorageFileNotFoundException;
import io.moresushant48.storage.StorageService;

@Controller
public class UploadController {

	@Autowired
    private FileSystemStorageService fileSystemStorageService;
	
    private final StorageService storageService;
    private User user;
    private File[] myFiles;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    FileRepository fileRepository;
    
    @Autowired
    public UploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    
    @GetMapping("/file-home")
    public ModelAndView fileHome(Principal principal) {
    	ModelAndView mv = new ModelAndView();
    	
    	fileSystemStorageService.init(principal.getName());
    	System.out.println(principal.getName());
    	user = userRepository.findIdByUsername(principal.getName());
		    	
		mv.setViewName("redirect:/list-files");
    	return mv;
    }
    
	@GetMapping("/list-files")
	public ModelAndView listUploadedFiles(Model model) throws IOException {
	
		ModelAndView mv = new ModelAndView();
		model.addAttribute("files", fileRepository.listFiles(user.getId()));
		mv.addObject("currentPage", "home");
		mv.setViewName("home");
		return mv; 
	}

    @GetMapping("/uploads/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

	@PostMapping("/upload-file")
	public String handleFileUpload(@RequestParam("file") MultipartFile[] files) {
		for(MultipartFile file : files) {
			myFiles = fileRepository.listFiles(user.getId());
			storageService.store(file, user, myFiles);
		}
		return "redirect:/list-files"; 
	}
	
	@GetMapping("/delete-file")
	public String deleteFile(@RequestParam("id") Long id, @RequestParam("name") String name) {
		
		fileSystemStorageService.deleteById(id,name);
		return "redirect:/list-files";
	}
	 
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}