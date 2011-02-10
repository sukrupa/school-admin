package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentsController {

    static final int NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE = 5;
    private static final List<String> STUDENT_CLASSES = Arrays.asList("Nursery", "LKG", "UKG", "1 Std", "2 Std", "3 Std", "4 Std", "5 Std", "6 Std", "7 Std", "8 Std", "9 Std", "10 Std");
    private static final List<String> GENDERS = Arrays.asList("Male", "Female");
    private static final List<String> CASTES = Arrays.asList("", "Achari", "Chettiyar", "Ganiga", "Gowda", "Gownder", "Naidu", "Okkaligaru", "SC", "Shetty", "ST", "Syed");
    private static final List<String> SUBCASTES = Arrays.asList("", "Banjarthi", "AK", "AD", " Kumbara");
    private static final List<String> COMMUNITY_LOCATIONS = Arrays.asList("", "Bhuvaneshwari Slum", "Chamundi Nagar", "Cholanaykanahalli", "Kunthigtrama", "Nagenahalli", "Subramnya Nagar");
    private static final List<String> RELIGIONS = Arrays.asList("", "Hindu", "Christian", "Muslim");

    private static final List<String> TALENTS = Arrays.asList("Sports", "Science Club", "Humanities", "Creative Writing",
            "Dancing", "Debate", "Singing", "Drama", "Musical Instrument", "Quiz", "Story Writing", "Choir", "Art", "Drawing", "Craft");
    static final String STUDENT_RECORD_UPDATED = "Student record updated";
    private StudentRepository repository;

    private static final int AGES_TO = 20;
    private static final int AGES_FROM = 2;


    @Autowired
    public StudentsController(StudentRepository repository) {
        this.repository = repository;
    }

    @RequestMapping()
    public String list(Map<String, List<?>> model) {
        List<Student> students = repository.findAll();
        model.put("pages", paginateStudents(students));
        return "students/list";
    }

    @RequestMapping(value = "list")
    public void searchResults(
            @ModelAttribute("searchParam") StudentSearchParameter searchParam,
            Map<String, List<?>> model) {
        if (searchParam.isAllBlank()) {
            list(model);
            return;
        }
        List<Student> students = repository.parametricSearch(searchParam);
        model.put("pages", paginateStudents(students));
    }

    private List<StudentListPage> paginateStudents(List<Student> students) {
        List<StudentListPage> pages = new ArrayList<StudentListPage>();
        while (students.size() > NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE) {
            pages.add(new StudentListPage(students.subList(0, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE)));
            students = students.subList(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE, students.size());
        }
        pages.add(new StudentListPage(students));
        return pages;
    }

    @RequestMapping(value = "search")
    public void search(Map<String, Object> model) {
        model.put("classes", STUDENT_CLASSES);
        model.put("genders", GENDERS);
        model.put("castes", CASTES);
        model.put("communityLocations", COMMUNITY_LOCATIONS);
        model.put("religions", RELIGIONS);
        model.put("agesFrom", getAges());
        model.put("agesTo", getAges());
        model.put("talents", TALENTS);
    }

    @RequestMapping(value = "update")
    public void update(@RequestParam String studentId, Map<String, Object> model) {
        Student theStudent = repository.load(studentId);

        model.put("classes", createDropDownList(theStudent.getStudentClass(), STUDENT_CLASSES));
        model.put("genders", createDropDownList(theStudent.getGender(), GENDERS));
        model.put("castes", createDropDownList(theStudent.getCaste(), CASTES));
        model.put("communityLocations", createDropDownList(theStudent.getCommunityLocation(), COMMUNITY_LOCATIONS));
        model.put("studentId", theStudent.getStudentId());
        model.put("name", theStudent.getName());
        model.put("dateOfBirth", theStudent.getDatofBirthForDisplay());
        model.put("religions", createDropDownList(theStudent.getReligion(), RELIGIONS));
        model.put("subcastes", createDropDownList(theStudent.getSubCaste(), SUBCASTES));
        model.put("father", theStudent.getFather());
        model.put("mother", theStudent.getMother());
        model.put("talents", createCheckBoxList(theStudent.talentDescriptions(), TALENTS));
    }

    @RequestMapping(value = "updateResults")
    public String confirmUpdateStudent(
            @ModelAttribute("updateStudent") UpdateStudentParameter studentParam,
            Map<String, Object> model) {

        Student updatedStudent = repository.update(studentParam);

        if (updatedStudent != null) {
            model.put("student", updatedStudent);
            model.put("studentUpdatedSuccesfullyMessage", STUDENT_RECORD_UPDATED);
            return "students/view";
        } else {
            model.put("message", "Error updating student");
            return "students/updateResults";
        }
    }

    private List<DropDownElement> createDropDownList(String selected, List<String> options) {
        List<DropDownElement> dropDownElements = new ArrayList<DropDownElement>();
        for (String genderString : options) {
            dropDownElements.add(new DropDownElement(genderString, genderString.equals(selected)));
        }
        return dropDownElements;
    }

    private List<CheckBoxElement> createCheckBoxList(List<String> studentTalents, List<String> allTalents) {
        List<CheckBoxElement> checkBoxElements = new ArrayList<CheckBoxElement>();
        for (String talent : allTalents) {
            checkBoxElements.add(new CheckBoxElement(talent, studentTalents.contains(talent)));
        }
        return checkBoxElements;
    }

    @RequestMapping(value = "{id}")
    public String view(@PathVariable String id, Map<String, Student> model) {
        Student student = repository.load(id);
        if (student != null) {
            model.put("student", student);
            return "students/view";
        }
        return "students/viewFailed";
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

    private class CheckBoxElement {
        public boolean isChecked() {
            return checked;
        }

        public String getValue() {
            return value;
        }

        private final String value;
        private final boolean checked;

        public CheckBoxElement(String value, boolean checked) {
            this.value = value;
            this.checked = checked;
        }
    }
}
