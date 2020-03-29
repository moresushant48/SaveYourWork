package io.moresushant48.servicesImpl;

import org.springframework.stereotype.Service;

@Service
public class FileService {

	/*@Autowired
	FileRepository fileRepository;
	
	public File storeFile(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file " + fileName);
        }
		
		try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new StorageException("Sorry! Filename contains invalid path sequence " + fileName);
	            }

	           // File dbFile = new File(fileName, file.getContentType(), file.);

	            return fileRepository.save(dbFile);
	        } catch (IOException ex) {
	            throw new StorageException("Could not store file " + fileName + ". Please try again!", ex);
	        }
	}

    public File getFile(String fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new StorageFileNotFoundException("File not found with id " + fileId));
    }

	public UploadFileResponse uploadFile(MultipartFile file) {
		File dbFile = storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
	}
	
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
	
	public File[] listFiles() {
		return fileRepository.listFiles();
	}*/
}
