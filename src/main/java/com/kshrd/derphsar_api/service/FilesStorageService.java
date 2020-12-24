package com.kshrd.derphsar_api.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

public interface FilesStorageService {

    public void init();
    public String save(MultipartFile file);
    public Resource load(String filename);
    public void deleteAll();
    public Stream<Path> loadAll();
    Set<String> listFilesUsingJavaIO(String dir);

}

