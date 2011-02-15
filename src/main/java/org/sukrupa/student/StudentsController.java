package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/students")
public class StudentsController {

    private static final List<String> STUDENT_CLASSES = Arrays.asList("Preschool", "LKG", "UKG", "1 Std", "2 Std", "3 Std", "4 Std", "5 Std", "6 Std", "7 Std", "8 Std", "9 Std", "10 Std");
    private static final List<String> GENDERS = Arrays.asList("Male", "Female");
    private static final List<String> CASTES = Arrays.asList("", "Achari", "Agnikula", "Arya Vashya", "Baljigru", "Bhramin", "Bohvi", "Chettyar",
            "Gowdas", "Gownder", "MBC", "Modahaliyar", "Nadar", "Naidu", "Nayak", "Others", "Rajput", "Rathore", "Reddy's", "SC", "Shalai Keta",
            "Shetty", "ST", "Tigalaru", "Vanniyar", "Vishwa Karma");
    private static final List<String> SUBCASTES = Arrays.asList("","Adi Drawida","Adi Janaga","Adi Karnataka","Bale -Balijigru","Bale Banjaguru","BC",
            "Bhajanthri","Ganiga Shetty","II 'A'","Kamala Achari","Kshathriya","Kumbar Shetty","Singh","Tiwari","Vailu Shetty","Vakkaliga",
            "Val Nayak","Vaniga Gownder","Vannikula");
    private static final List<String> COMMUNITY_LOCATIONS = Arrays.asList("", "Bhuvaneshwari Slum", "Chamundi Nagar",
            "Cholanayakanhalli", "Ganganagar", "Guddadahalli", "Hebbal", "Kanakanagar", "Kunthigrama", "Nagenahalli",
            "Rehmath Nagar", "Residential", "Subramanyanagar");
    private static final List<String> RELIGIONS = Arrays.asList("", "Christian", "Hindu", "Muslim", "Sikh");
    private static final List<String> TALENTS = Arrays.asList("Acting", "Arts & Crafts", "Creative Writing", "Dancing", "Mimicry",
            "Musical Instrument", "Pick & Speak", "Public Speaking", "Reading", "Singing", "Sports", "Story Telling");

    private StudentService service;

    private static final int AGES_TO = 20;
    private static final int AGES_FROM = 2;


    @Autowired
    public StudentsController(StudentService service) {
        this.service = service;
    }

    @RequestMapping()
    public String list(@RequestParam(required = false, defaultValue = "1", value = "page") int pageNumber,
                       @ModelAttribute("searchParam") StudentSearchParameter searchParam,
                       Map<String, Object> model, HttpServletRequest request) {
        StudentListPage students = service.getPage(searchParam, pageNumber, request.getQueryString());
        model.put("page", students);
        return "students/list";
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

    @RequestMapping(value = "{id}/edit", method = GET)
    public String edit(@PathVariable String id,
                       @RequestParam(required = false, defaultValue = "") String noteUpdateStatus,
                       @RequestParam(required = false) boolean noteAddedSuccesfully,
                       Map<String, Object> model) {
        Student theStudent = service.load(id);

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

        model.put("note_message", noteUpdateStatus);
        model.put("noteAddedSuccesfully", noteAddedSuccesfully);

        return "students/edit";
    }

    @RequestMapping(value = "{id}", method = POST)
    public String confirmUpdateStudent(
            @PathVariable String id,
            @ModelAttribute("updateStudent") UpdateStudentParameter studentParam,
            Map<String, Object> model) {

        Student updatedStudent = service.update(studentParam);

        if (updatedStudent != null) {
            model.put("student", updatedStudent);
	        model.put("studentUpdatedSuccesfully", true);
            return format("redirect:/students/%s", id);
        }else {
            model.put("message","Error updating student");
            return format("redirect:/students/%s/edit", id);
        }
    }

    @RequestMapping(value = "{id}", method = GET)
    public String view(@PathVariable String id,
                       @RequestParam(required = false) boolean studentUpdatedSuccesfully,
                       Map<String, Object> model) {
	    Student student = service.load(id);
	    if (student != null) {
			model.put("student", student);
			model.put("studentUpdatedSuccesfully",studentUpdatedSuccesfully);
			return "students/view";
	    }
	    return "students/viewFailed";
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
