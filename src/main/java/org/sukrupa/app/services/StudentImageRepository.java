package org.sukrupa.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.sukrupa.platform.config.AppConfiguration;
import org.sukrupa.student.StudentService;

import java.io.File;
import java.io.FileNotFoundException;

@Service
public class StudentImageRepository {

    private static final String PLACEHOLDER_IMAGE = "placeholderImage";
    private FileFactory fileFactory;
    private String imageRepositoryLocation;

    @Autowired
    public StudentImageRepository(FileFactory fileFactory, @Value(value = "${app.image.dir}") String imageRepositoryLocation) {
        this.fileFactory = fileFactory;
        this.imageRepositoryLocation = imageRepositoryLocation;
    }

    public File load(String fileName) throws FileNotFoundException {
        File image;
        try {
            image = fileFactory.create(imageRepositoryLocation + fileName);
        } catch (FileNotFoundException e) {
            image = fileFactory.create(imageRepositoryLocation + PLACEHOLDER_IMAGE);
        }
        return image;
    }

}
