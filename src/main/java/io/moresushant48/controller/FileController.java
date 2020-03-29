package io.moresushant48.controller;

import org.springframework.stereotype.Controller;

@Controller
public class FileController {

	/*
	 * @Autowired private FileService fileService;
	 * 
	 * @PostMapping("/upload-file") public ModelAndView
	 * uploadFile(@RequestParam("file") MultipartFile[] files) { ModelAndView mv =
	 * new ModelAndView();
	 * 
	 * fileService.uploadMultipleFiles(files);
	 * 
	 * mv.setViewName("home"); return mv; }
	 * 
	 * @GetMapping("/list-files") public ModelAndView listFile() { ModelAndView mv =
	 * new ModelAndView();
	 * 
	 * mv.addObject("files", fileService.listFiles()); Object principal =
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal(); String
	 * username; if(principal instanceof UserDetails) { username =
	 * ((UserDetails)principal).getUsername(); } else { username =
	 * principal.toString(); }
	 * 
	 * System.out.println(username); mv.setViewName("home"); return mv; }
	 * 
	 * 
	 * @PostMapping("/upload-file") public UploadFileResponse
	 * uploadFile(@RequestParam("file") MultipartFile file) { File dbFile =
	 * fileService.storeFile(file);
	 * 
	 * String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	 * .path("/downloadFile/") .path(dbFile.getId()) .toUriString();
	 * 
	 * return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
	 * file.getContentType(), file.getSize()); }
	 * 
	 * 
	 * 
	 * @PostMapping("/uploadMultipleFiles") public List<UploadFileResponse>
	 * uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) { return
	 * Arrays.asList(files) .stream() .map(file -> uploadFile(file))
	 * .collect(Collectors.toList()); }
	 * 
	 * 
	 * @GetMapping("/downloadFile/{fileId}") public ResponseEntity<Resource>
	 * downloadFile(@PathVariable String fileId) { // Load file from database File
	 * dbFile = fileService.getFile(fileId);
	 * 
	 * return ResponseEntity.ok()
	 * .contentType(MediaType.parseMediaType(dbFile.getFileType()))
	 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
	 * dbFile.getFileName() + "\"") .body(new ByteArrayResource(dbFile.getData()));
	 * }
	 */
}
