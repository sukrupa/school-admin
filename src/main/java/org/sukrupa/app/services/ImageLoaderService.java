package org.sukrupa.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.sukrupa.platform.config.AppConfiguration;

import java.io.File;
import java.io.FileNotFoundException;

public class ImageLoaderService {

    private static final String PLACEHOLDER_IMAGE = "placeholderImage";
    private FileFactory fileFactory;
    private String imageRepositoryLocation;

    @Autowired
    public ImageLoaderService(FileFactory fileFactory, @Value(value="${app.image.dir}")String imageRepositoryLocation) {
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
