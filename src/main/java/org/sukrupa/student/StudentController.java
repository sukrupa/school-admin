package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private static final String UPDATE_VIEW = "studentUpdate";
    private static final String UPDATE_RESULTS_VIEW = "studentUpdateResults";
    private static final String STUDENT_VIEW = "student";
    private static final List<String> STUDENT_CLASSES = Arrays.asList("Nursery", "LKG", "UKG", "1 Std", "2 Std", "3 Std", "4 Std", "5 Std", "6 Std", "7 Std", "8 Std", "9 Std", "10 Std");
    private static final List<String> GENDERS = Arrays.asList("Male", "Female");
    private static final List<String> CASTES = Arrays.asList("Achari", "Chettiyar", "Ganiga", "Gowda", "Gownder", "Naidu", "Okkaligaru", "SC", "Shetty", "ST", "Syed");
    private static final List<String> COMMUNITY_LOCATIONS = Arrays.asList("Bhuvaneshwari Slum", "Chamundi Nagar", "Cholanaykanahalli", "Kunthigtrama", "Nagenahalli", "Subramnya Nagar");
    private static final List<String> RELIGIONS = Arrays.asList("Hindu", "Christian", "Muslim");
    private static final List<String> TALENTS = Arrays.asList("Sports", "Science Club", "Humanities", "Creative Writing",
            "Dancing", "Debate", "Singing", "Drama", "Musical Instrument", "Quiz", "Story Writing", "Choir", "Art", "Drawing", "Craft");

    private StudentRepository repository;
    private static final String ANY = "Any";

    private static final int AGES_TO = 20;
    private static final int AGES_FROM = 2;

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
        model.put("communityLocations", COMMUNITY_LOCATIONS);
        model.put("religions", RELIGIONS);
        model.put("agesFrom", getAges());
        model.put("agesTo", getAges());
        model.put("talents", TALENTS);
        return SEARCH_VIEW;
    }

    @RequestMapping(value = "update")
    public String updateStudent(Map<String, Object> model) {
        Student theStudent = repository.findAll().get(0);

        model.put("classes", createDropDownList(theStudent.getStudentClass(), STUDENT_CLASSES));
        model.put("genders", createDropDownList(theStudent.getGender(), GENDERS));
        model.put("castes", createDropDownList(theStudent.getCaste(), CASTES));
        model.put("areas", createDropDownList(theStudent.getCommunityLocation(), COMMUNITY_LOCATIONS));
        model.put("studentId", theStudent.getStudentId());
        model.put("name", theStudent.getName());
        model.put("dateOfBirth", theStudent.getDateOfBirth().toString());
        model.put("religion", theStudent.getReligion());
        model.put("subCaste", theStudent.getSubCaste());
        model.put("father", "");
        model.put("mother", "");
        model.put("talents", "");
        return UPDATE_VIEW;
    }

    @RequestMapping(value = "updateResults")
    public String confirmUpdateStudent(
            @ModelAttribute("updateStudent") UpdateStudentParameter studentParam,
            Map<String, Object> model) {
        boolean succeeded = repository.update(studentParam);

        model.put("message", succeeded ? "Student updated successfully" : "Error updating student");

        return UPDATE_RESULTS_VIEW;
    }

    private List<DropDownElement> createDropDownList(String selected, List<String> options) {
        List<DropDownElement> dropDownElements = new ArrayList<DropDownElement>();
        for (String genderString : options) {
            dropDownElements.add(new DropDownElement(genderString, genderString.equals(selected)));
        }
        return dropDownElements;
    }

    @RequestMapping(value = "{id}")
    public String find(@PathVariable String id, Map<String, Student> model) {
        Student student = repository.find(id);
        model.put("student", student);
        return STUDENT_VIEW;

    }

    private List<String> getAges() {
        List<String> ages = new ArrayList<String>();

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
