package org.sukrupa.app.services;


import org.junit.Before;
import org.junit.Test;
import org.sukrupa.platform.config.AppConfiguration;
import org.sukrupa.student.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentImageRepositoryTest {
    private StudentImageRepository studentImageRepository;
    private FileHandler fileHandler = mock(FileHandler.class);
    private String imageRepositoryLocation;

    @Before
    public void setUp(){
        imageRepositoryLocation = new AppConfiguration().properties().getProperty("app.image.dir");
        studentImageRepository = new StudentImageRepository(fileHandler, imageRepositoryLocation);
    }

    @Test
    public void whenPassedAValidFileNameShouldReturnAFileObject() throws FileNotFoundException {
        File file = mock(File.class);
        when(fileHandler.create(imageRepositoryLocation + "validFile")).thenReturn(file);

        assertEquals(new Image(file), studentImageRepository.load("validFile"));
    }

    @Test
    public void whenPassedAnInvalidFileNameShouldReturnPlaceHolder() throws FileNotFoundException {
        File placeholderImage = mock(File.class);
        when(fileHandler.create(imageRepositoryLocation + "invalidFile")).thenThrow(new FileNotFoundException());
        when(fileHandler.create(imageRepositoryLocation + "placeholderImage")).thenReturn(placeholderImage);

        assertEquals(new Image(placeholderImage), studentImageRepository.load("invalidFile"));
    }

    @Test
    public void whenPassedAnImageShouldSaveTheImageWithTheSameName() throws IOException {
        Image newImage = mock(Image.class);
        InputStream inputStream = mock(InputStream.class);
        when(newImage.getInputStream()).thenReturn(inputStream);
        studentImageRepository.save(newImage, "12345");
        verify(fileHandler).save(imageRepositoryLocation, "12345", inputStream);
    }
}
