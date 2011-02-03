package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sukrupa.platform.DoNotRemove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentController {

    private static final String STUDENTS_MODEL = "students";
    private static final String STUDENTS_VIEW = "students";
    private static final String SEARCH_VIEW = "studentSearch";
    private static final String UPDATE_VIEW = "update";
	private static final int AGES_TO = 18;
	private static final int AGES_FROM = 2;
    private static final String STUDENT_VIEW = "student";

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

	@RequestMapping(value = "searchResult")
    @Transactional
	public String parametricSearchResult(
			@ModelAttribute("searchParam") SearchParameter searchParam,
			Map<String, List<Student>> model) {

		model.put(STUDENTS_MODEL, repository.parametricSearch(searchParam.getStudentClass(), searchParam.getGender(),
				searchParam.getCaste(), searchParam.getArea(), searchParam.getAgeFrom(), searchParam.getAgeTo(), searchParam.getTalent()));

        return STUDENTS_VIEW;
    }

	@RequestMapping(value = "search")
	public String parametricSearch(Map<String, Object> model) {
		model.put("classes", Arrays.asList("", "Nursery", "LKG", "UKG", "1 Std", "2 Std", "3 Std", "4 Std", "5 Std", "6 Std", "7 Std", "8 Std", "9 Std", "10 Std"));
	    model.put("genders", Arrays.asList("", "Male", "Female"));
		model.put("castes", Arrays.asList("", "Some caste"));
		model.put("areas", Arrays.asList(""));
		model.put("agesFrom", getAges());
		model.put("agesTo", getAges());
		model.put("talents", Arrays.asList("", "Sports","Science Club", "Humanities", "Creative Writing",
				"Dancing","Debate","Singing","Drama","Musical Instrument","Quiz","Story Writing","Choir","Art","Drawing","Craft"));
		return SEARCH_VIEW;
	}

    @RequestMapping(value = "update")
    @Transactional
    public String updateStudent(Map<String, List<String>> model){

        //hard-coded student
        //TODO: get actual student from search results
        Student theStudent = repository.findAll().get(0);

        model.put("studentId",Arrays.asList(theStudent.getStudentId()));
        model.put("name",Arrays.asList(theStudent.getName()));
        model.put("dateOfBirth",Arrays.asList(theStudent.getDateOfBirth().toString()));
        model.put("gender",Arrays.asList(theStudent.getGender()));
        model.put("religion",Arrays.asList(theStudent.getReligion()));
        model.put("caste",Arrays.asList(theStudent.getCaste()));
        model.put("subCaste",Arrays.asList(theStudent.getSubCaste()));
        model.put("area",Arrays.asList(theStudent.getArea()));
        model.put("father",Arrays.asList(""));
        model.put("mother",Arrays.asList(""));
        model.put("talents",Arrays.asList(""));
        return UPDATE_VIEW;
    }

    @RequestMapping(value = "{id}")
    @Transactional
    public String find(@PathVariable String id, Map<String, Student> model) {
        Student student = repository.find(id);
        model.put("student", student);
        return STUDENT_VIEW;

    }


	private List<String> getAges(){
		List<String> ages = new ArrayList<String>();

		ages.add("");
		for(int age = AGES_FROM; age <= AGES_TO; age++) {
			ages.add(age+"");
		}

		return ages;
	}
}
