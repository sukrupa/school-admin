package org.sukrupa.app.httpcommunicationservices;

import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.app.services.FileHandler;
import org.sukrupa.app.services.StudentImageRepository;
import org.sukrupa.app.students.StudentImageView;
import org.sukrupa.app.students.image.StudentImageController;
import org.sukrupa.platform.config.AppConfiguration;
import org.sukrupa.student.StudentRepository;

import java.awt.font.ImageGraphicAttribute;
import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ImageRequestControllerTest {


    private ImageRequestController imageRequestController;
    private String imageRepositoryLocation;
    private FileHandler imageHandler;


    public ImageRequestControllerTest(){
        imageRepositoryLocation = new AppConfiguration().properties().getProperty("app.image.dir");
        imageHandler = mock(FileHandler.class);
        this.imageRequestController = new ImageRequestController(new StudentImageRepository(imageHandler,imageRepositoryLocation));
    }

    @Test
    public void shouldRetrieveImageFromStudentRecord() throws FileNotFoundException {
        File mockImage = mock(File.class);
        when(imageHandler.create("image")).thenReturn(mockImage);
        assertTrue(imageRequestController.getImage("image") instanceof StudentImageView);
    }
}
