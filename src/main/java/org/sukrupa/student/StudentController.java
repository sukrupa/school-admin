package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
			@RequestParam(value = "searchParam") SearchParameter searchParam,
//			@RequestParam(value = "class") String studentClass,
//			@RequestParam(value = "gender") String gender,
//			@RequestParam(value = "caste") String caste,
//			@RequestParam(value = "area") String area,
//			@RequestParam(value = "ageFrom") String ageFrom,
//			@RequestParam(value = "ageTo") String ageTo,
//			@RequestParam(value = "talent") String talent,
			Map<String, List<Student>> model) {

		model.put(STUDENTS_MODEL, repository.parametricSearch(searchParam.getStudentClass(), searchParam.getGender(),
				searchParam.getCaste(), searchParam.getArea(), searchParam.getAgeFrom(), searchParam.getAgeTo(), searchParam.getTalent()));

        return STUDENTS_VIEW;
    }

	@RequestMapping(value = "search")
	public String parametricSearch(Map<String, List<String>> model) {
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
    public String updateStudent(Map<String, List<String>> model){
        model.put("studentId",Arrays.asList("yo"));
        model.put("name",Arrays.asList(""));
        model.put("dateOfBirth",Arrays.asList(""));
        model.put("gender",Arrays.asList(""));
        model.put("religion",Arrays.asList(""));
        model.put("caste",Arrays.asList(""));
        model.put("subCaste",Arrays.asList(""));
        model.put("area",Arrays.asList(""));
        model.put("father",Arrays.asList(""));
        model.put("mother",Arrays.asList(""));
        model.put("talents",Arrays.asList("Sports","Science Club","Quiz"));
        return UPDATE_VIEW;
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
