package org.sukrupa.app.services;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;


@Component
public class FileHandler {
    public File create(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if(!file.exists()){
            throw new FileNotFoundException("Image not found");
        }
        return file;
    }

    public void save(String imageRepositoryLocation, String name, InputStream inputStream) {

    }
}
