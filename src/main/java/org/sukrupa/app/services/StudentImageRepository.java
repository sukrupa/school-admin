package org.sukrupa.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.sukrupa.student.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class StudentImageRepository {

    private static final String PLACEHOLDER_IMAGE = "placeholderImage";
    private FileHandler fileHandler;
    private String imageRepositoryLocation;

    @Autowired
    public StudentImageRepository(FileHandler fileHandler, @Value(value = "${app.image.dir}") String imageRepositoryLocation) {
        this.fileHandler = fileHandler;
        this.imageRepositoryLocation = imageRepositoryLocation;
    }

    public File load(String fileName) throws FileNotFoundException {
        File image;
        try {
            image = fileHandler.create(imageRepositoryLocation + fileName);
        } catch (FileNotFoundException e) {
            image = fileHandler.create(imageRepositoryLocation + PLACEHOLDER_IMAGE);
        }
        return image;
    }

    public boolean save(Image image, String id) {
        try{
         fileHandler.save(imageRepositoryLocation,id,image.getInputStream());
        }catch (IOException e){
            return false;
        }
        return true;
    }
}
