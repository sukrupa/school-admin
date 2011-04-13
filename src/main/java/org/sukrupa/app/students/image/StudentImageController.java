package org.sukrupa.app.students.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.sukrupa.app.services.StudentImageRepository;
import org.sukrupa.app.students.StudentImageView;

import java.io.*;

import static java.lang.String.format;

@Controller
@RequestMapping("/students")
public class StudentImageController {

    private StudentImageRepository studentImageRepository;

    @Autowired
    public StudentImageController(StudentImageRepository studentImageRepository){
        this.studentImageRepository = studentImageRepository;
    }

    @RequestMapping(value = "{id}/image")
    public View getImage(@PathVariable String id) throws FileNotFoundException{
        return new StudentImageView(studentImageRepository.load(id));
    }
}
