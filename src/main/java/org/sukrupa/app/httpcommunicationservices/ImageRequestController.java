package org.sukrupa.app.httpcommunicationservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.sukrupa.app.services.StudentImageRepository;
import org.sukrupa.app.students.image.StudentImageController;

import java.io.FileNotFoundException;

@Controller
@RequestMapping("/getstudentimage")
public class ImageRequestController {
    private StudentImageRepository studentImageRepository;
    private StudentImageController studentImageController;

    @Autowired
    public ImageRequestController(StudentImageRepository studentImageRepository) {

        this.studentImageRepository = studentImageRepository;
        studentImageController = new StudentImageController(studentImageRepository);
    }

    @RequestMapping(value = "{id}/image")
    public View getImage(@PathVariable String id) throws FileNotFoundException {
        return studentImageController.getImage(id);
    }
}
