package io.moresushant48.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.moresushant48.Repository.FileRepository;
import io.moresushant48.model.Access;
import io.moresushant48.model.User;

@Service
public class FileSystemStorageService implements StorageService {

    private static Path rootLocation = null;
        
    private io.moresushant48.model.File myFile;
    
    @Autowired
    FileRepository fileRepository;
    
	@Override
    public void store(MultipartFile file, User user, ArrayList<io.moresushant48.model.File> myFiles, int accessId) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
//            if (file.isEmpty()) {
//                throw new StorageException("Failed to store empty file " + filename);
//            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
            	
            	System.out.println(FileSystemStorageService.rootLocation);
            	
            	boolean value = false;
            	
            	/*
            	 * Overwrite the file if the same named file exists in the database.
            	 */
            	for(io.moresushant48.model.File tempFile : myFiles) {
            		if(tempFile.getFileName().equals(file.getOriginalFilename())) {
            			
            			tempFile.setFileSize(String.format("%.2f", (double)file.getSize() / (1024*1024)) + " Mb");
                		fileRepository.save(tempFile);
                		value = true;
                		break;
            		}
            	}
            	
            	/*
            	 * Fetch the Current User entity and setUser() in File entity.
            	 */
            	
            	myFile = new io.moresushant48.model.File();
            	
            	if(!value) {
		        	myFile.setFileName(file.getOriginalFilename());
		        	myFile.setFileType(file.getContentType());
		        	myFile.setFileSize(String.format("%.2f", (double)file.getSize() / (1024*1024)) + " Mb");
		        	myFile.setUser(user);
		        	myFile.setAccess(new Access(accessId));
		        	fileRepository.save(myFile);
            	}
            	
            	/*
            	 * Save the files.
            	 */
                Files.copy(inputStream, FileSystemStorageService.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }
	
    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(FileSystemStorageService.rootLocation, 1)
                .filter(path -> !path.equals(FileSystemStorageService.rootLocation))
                .map(FileSystemStorageService.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
    
    @Override
	public void deleteById(Long id, String name) {
		try {
			
			File file = new File(FileSystemStorageService.rootLocation + "\\" + name);
			Files.deleteIfExists(file.toPath());			
			fileRepository.deleteById(id);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init(String username) {
    	
    	File file = new File("\\files\\" + username);
    	
        try {
        	FileSystemStorageService.rootLocation =  Paths.get(file.toURI());
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
        catch (NullPointerException e1) {
			e1.printStackTrace();
		}
    }
}