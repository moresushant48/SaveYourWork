package io.moresushant48.servicesImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.moresushant48.Repository.FileRepository;
import io.moresushant48.model.File;
import io.moresushant48.storage.StorageException;
import io.moresushant48.storage.StorageFileNotFoundException;
import io.moresushant48.storage.UploadFileResponse;

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
