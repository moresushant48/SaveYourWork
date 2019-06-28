package io.moresushant48.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

	private static final String UPLOADED_FOLDER = "S://files//";

	public void uploadFile(MultipartFile file) throws IOException {
		
		InputStream inputStream = file.getInputStream();
		File targetFile = new File(UPLOADED_FOLDER + file.getOriginalFilename());
		
		byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
	}
}
