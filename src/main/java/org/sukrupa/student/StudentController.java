package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private static final String CONFIRM_UPDATE_VIEW = "confirmUpdate";
    private static final String STUDENT_VIEW = "student";

    private static final int AGES_TO = 18;
    private static final int AGES_FROM = 2;
    private StudentRepository repository;
    private final List<String> STUDENT_CLASSES = Arrays.asList("", "Nursery", "LKG", "UKG", "1 Std", "2 Std", "3 Std", "4 Std", "5 Std", "6 Std", "7 Std", "8 Std", "9 Std", "10 Std");
    private final List<String> GENDERS = Arrays.asList("", "Male", "Female");
    private final List<String> CASTES = Arrays.asList("", "Achari", "Chettiyar", "Ganiga", "Gownder", "Naidu", "Okkaligaru", "SC", "Shetty", "ST");
    private final List<String> AREAS = Arrays.asList("", "Bhuvaneshwari Slum", "Chamundi Nagar", "Cholanaykanahalli", "Kunthigtrama", "Nagenahalli", "Subramnya Nagar");

    @DoNotRemove
    StudentController() {
    }

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @RequestMapping()
    public String all(Map<String, List<Student>> model) {
        model.put(STUDENTS_MODEL, repository.findAll());
        return STUDENTS_VIEW;
    }

    @RequestMapping(value = "searchResult")
    @Transactional
    public String parametricSearchResult(
            @ModelAttribute("searchParam") StudentSearchParameter searchParam,
            Map<String, List<Student>> model) {
        model.put(STUDENTS_MODEL, repository.parametricSearch(searchParam));
        return STUDENTS_VIEW;
    }

    @RequestMapping(value = "search")
    public String parametricSearch(Map<String, Object> model) {
        model.put("classes", STUDENT_CLASSES);
        model.put("genders", GENDERS);
        model.put("castes", CASTES);
        model.put("areas", AREAS);
        model.put("agesFrom", getAges());
        model.put("agesTo", getAges());
        model.put("talents", Arrays.asList("", "Sports", "Science Club", "Humanities", "Creative Writing",
                "Dancing", "Debate", "Singing", "Drama", "Musical Instrument", "Quiz", "Story Writing", "Choir", "Art", "Drawing", "Craft"));
        return SEARCH_VIEW;
    }

    @RequestMapping(value = "update")
    @Transactional
    public String updateStudent(Map<String, List<?>> model) {

        Student theStudent = repository.findAll().get(0);

        model.put("classes", createDropDownList(theStudent.getStudentClass(), STUDENT_CLASSES));
        model.put("genders", createDropDownList(theStudent.getGender(), GENDERS));
        model.put("castes", createDropDownList(theStudent.getCaste(), CASTES));
        model.put("areas", createDropDownList(theStudent.getArea(), AREAS));

        model.put("studentId", Arrays.asList(theStudent.getStudentId()));
        model.put("name", Arrays.asList(theStudent.getName()));
        model.put("dateOfBirth", Arrays.asList(theStudent.getDateOfBirth().toString()));
        model.put("religion", Arrays.asList(theStudent.getReligion()));
        model.put("subCaste", Arrays.asList(theStudent.getSubCaste()));
        model.put("father", Arrays.asList(""));
        model.put("mother", Arrays.asList(""));
        model.put("talents", Arrays.asList(""));
        return UPDATE_VIEW;
    }

    @RequestMapping(value = "updateConfirm")
    @Transactional
    public String confirmUpdateStudent(
            @ModelAttribute("updateStudent") UpdateStudentParameter studentParam,
            Map<String, List<?>> model) {
        repository.update(studentParam);
        return CONFIRM_UPDATE_VIEW;
    }

    private List<DropDownElement> createDropDownList(String selected, List<String> options) {
        List<DropDownElement> genders = new ArrayList<DropDownElement>();
        for (String genderString : options) {
            genders.add(new DropDownElement(genderString, genderString.equals(selected)));
        }
        return genders;
    }

    @RequestMapping(value = "{id}")
    @Transactional
    public String find(@PathVariable String id, Map<String, Student> model) {
        Student student = repository.find(id);
        model.put("student", student);
        return STUDENT_VIEW;

    }


    private List<String> getAges() {
        List<String> ages = new ArrayList<String>();

        ages.add("");
        for (int age = AGES_FROM; age <= AGES_TO; age++) {
            ages.add(age + "");
        }

        return ages;
    }

    private class DropDownElement {
        public boolean isSelected() {
            return selected;
        }

        public String getValue() {
            return value;
        }

        private final String value;
        private final boolean selected;

        public DropDownElement(String value, boolean selected) {
            this.value = value;
            this.selected = selected;
        }
    }
}
