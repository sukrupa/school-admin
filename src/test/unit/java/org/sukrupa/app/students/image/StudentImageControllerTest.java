package org.sukrupa.app.students.image;


import org.junit.Test;
import org.sukrupa.app.services.FileHandler;
import org.sukrupa.app.services.StudentImageRepository;
import org.sukrupa.app.students.StudentImageView;
import org.sukrupa.platform.config.AppConfiguration;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentImageControllerTest {

    private StudentImageController studentImageController;
    private String imageRepositoryLocation;
    private FileHandler imageHandler;

    public StudentImageControllerTest() {
        imageRepositoryLocation = new AppConfiguration().properties().getProperty("app.image.dir");
        imageHandler = mock(FileHandler.class);
        this.studentImageController = new StudentImageController(new StudentImageRepository(imageHandler,imageRepositoryLocation));
    }

    @Test
    public void testRequestForAnImageMustReturnAnImageView() throws FileNotFoundException {
        File mockImage = mock(File.class);
        when(imageHandler.create("image")).thenReturn(mockImage);
        assertTrue(studentImageController.getImage("image") instanceof StudentImageView);
    }
}
