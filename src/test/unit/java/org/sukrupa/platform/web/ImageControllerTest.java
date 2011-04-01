package org.sukrupa.platform.web;


import org.junit.Test;
import org.sukrupa.app.students.StudentImageView;

import javax.swing.text.html.ImageView;

import static org.junit.Assert.assertTrue;

public class ImageControllerTest {

    private ImageController imageController;

    public ImageControllerTest() {
        this.imageController = new ImageController();
    }

    @Test
    public void testRequestForAnImageMustReturnAnImageView(){
        assertTrue(imageController.getImage() instanceof StudentImageView);
    }
}
