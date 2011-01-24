package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentController {

    private static final String STUDENTS_MODEL = "students";
    private static final String STUDENTS_VIEW = "students";

    private final StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @RequestMapping()
    public String all(Map<String, List<Student>> model) {
        model.put(STUDENTS_MODEL, repository.findAll());
        return STUDENTS_VIEW;
    }
}
