package org.sukrupa.app.services;


import org.junit.Before;
import org.junit.Test;
import org.sukrupa.platform.config.AppConfiguration;

import java.io.File;
import java.io.FileNotFoundException;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentImageRepositoryTest {
    private StudentImageRepository studentImageService;
    private FileFactory fileFactory = mock(FileFactory.class);
    private String imageRepositoryLocation;

    @Before
    public void setUp(){
        imageRepositoryLocation = new AppConfiguration().properties().getProperty("app.image.dir");
        studentImageService = new StudentImageRepository(fileFactory, imageRepositoryLocation);
    }

    @Test
    public void whenPassedAValidFileNameShouldReturnAFileObject() throws FileNotFoundException {
        File file = mock(File.class);
        when(fileFactory.create(imageRepositoryLocation + "validFile")).thenReturn(file);

        assertEquals(file, studentImageService.load("validFile"));
    }

    @Test
    public void whenPassedAnInvalidFileNameShouldReturnPlaceHolder() throws FileNotFoundException {
        File placeholderImage = mock(File.class);
        when(fileFactory.create(imageRepositoryLocation + "invalidFile")).thenThrow(new FileNotFoundException());
        when(fileFactory.create(imageRepositoryLocation + "placeholderImage")).thenReturn(placeholderImage);

        assertEquals(placeholderImage, studentImageService.load("invalidFile"));
    }


}