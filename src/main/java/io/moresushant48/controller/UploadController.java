package io.moresushant48.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import io.moresushant48.storage.FileSystemStorageService;
import io.moresushant48.storage.StorageFileNotFoundException;
import io.moresushant48.storage.StorageService;

@Controller
public class UploadController {

	@Autowired
    private FileSystemStorageService fileSystemStorageService;
	
    private final StorageService storageService;

    @Autowired
    public UploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    
    @GetMapping("/file-home")
    public ModelAndView fileHome(Principal principal) {
    	ModelAndView mv = new ModelAndView();
    	fileSystemStorageService.init(principal.getName());
		System.out.println(principal.getName());
		mv.setViewName("redirect:/list-files");
    	return mv;
    }
    
	@GetMapping("/list-files") 
	public ModelAndView listUploadedFiles(Model model) throws IOException {
	
		ModelAndView mv = new ModelAndView();
		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(UploadController.class, 
						"serveFile", path.getFileName().toString()).build().toString())
							.collect(Collectors.toList()));
		mv.addObject("currentPage", "home");
		mv.setViewName("home");
		return mv; 
	}
	 

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

	@PostMapping("/upload-file")
	public String handleFileUpload(@RequestParam("file") MultipartFile[] files) {
		for(MultipartFile file : files) {
			storageService.store(file);
		}
		return "redirect:/list-files"; 
	}
	 
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}