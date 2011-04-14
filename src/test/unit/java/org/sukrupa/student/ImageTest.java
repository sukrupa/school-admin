package org.sukrupa.student;

import org.hibernate.type.descriptor.BinaryStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ImageTest {

    private final File photoFile = new File(System.getProperty("user.home") + "/.sukrupa/images/placeholderImage");

    @Mock
    CommonsMultipartFile uploadedPhoto;


    @Test
    public void testInputStreamHasSomeDataWhenImageInstantiatedWithACommonsMultipartFile() throws Exception {
        Image image = new Image(uploadedPhoto);
        InputStream mockInputStream = mock(InputStream.class);
        when(uploadedPhoto.getInputStream()).thenReturn(mockInputStream);
        when(mockInputStream.available()).thenReturn(1);
        assertTrue(image.getInputStream().available() != 0);
    }

    @Test
    public void testInputStreamHasSomeDataWhenImageInstantiatedWithAFile() throws IOException{
        Image image = new Image(photoFile);
        assertTrue(image.getInputStream().available() != 0);
    }
}
