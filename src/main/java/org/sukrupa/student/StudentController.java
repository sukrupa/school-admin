package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sukrupa.platform.DoNotRemove;

import java.util.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private static final String STUDENTS_MODEL = "students";
    private static final String STUDENTS_VIEW = "students";
    private static final String SEARCH_VIEW = "studentSearchSingleParameter";

    private StudentRepository repository;

    @DoNotRemove
    StudentController() {
    }

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @RequestMapping()
    @Transactional
    public String all(Map<String, List<Student>> model) {
        model.put(STUDENTS_MODEL, repository.findAll());
        return STUDENTS_VIEW;
    }

	@RequestMapping(value = "parametricSearchResult")
    @Transactional
	public String parametricSearchResult(@RequestParam(value = "class") String studentClass, Map<String, List<Student>> model) {
		model.put(STUDENTS_MODEL, repository.singleParametricSearch(studentClass));
        return STUDENTS_VIEW;
    }

	@RequestMapping(value = "search")
	public String parametricSearch(Map<String, List<String>> model) {
		model.put("classes", Arrays.asList("Nursery", "LKG","UKG","1 Std","2 Std","3 Std","4 Std","5 Std","6 Std","7 Std","8 Std","9 Std","10 Std"));
		return SEARCH_VIEW;
    }


}
