package io.moresushant48.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import io.moresushant48.model.File;
import io.moresushant48.model.User;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public interface StorageService {

    void init(String username);
    
    void store(MultipartFile file, User user, ArrayList<File> myFiles);

    void deleteById(Long id, String name);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
    
}