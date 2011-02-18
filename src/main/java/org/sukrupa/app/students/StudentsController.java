package org.sukrupa.app.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.sukrupa.student.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/students")
public class StudentsController {

    private StudentService service;

    private static final int AGES_TO = 20;
    private static final int AGES_FROM = 2;


    @Autowired
    public StudentsController(StudentService service) {
        this.service = service;
    }

    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1", value = "page") int pageNumber,
                       @ModelAttribute("searchParam") StudentSearchParameter searchParam,
	    Map<String, Object> model, HttpServletRequest request) {
        StudentListPage students = service.getPage(searchParam, pageNumber, request.getQueryString());

        if (students.getStudents().isEmpty()) {
            return "students/listEmpty";
        }
        
        model.put("page", students);

        return "students/list";
    }


    // @RequestMapping(value = "promote" , method = POST) feature not available yet
    public String promoteClass(Map<String,Integer> model){
      int promoteStudentsCount= service.promoteStudentsToNextClass();
      model.put("numberOfStudentsUpdated",promoteStudentsCount);
      return "redirect:/students/update-successful";
    }

    @RequestMapping(value = "update-successful")
    public String updateSuccessful(@RequestParam("numberOfStudentsUpdated") int promoteStudentCount , Map<String,Integer> model) {

        model.put("numberOfStudentsUpdated", promoteStudentCount);
       return "students/updateSuccessful";
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


    @RequestMapping(value = "search")
    public void search(Map<String, Object> model) {
        model.put("classes", StudentOptionsGenerator.STUDENT_CLASSES);
        model.put("genders", StudentOptionsGenerator.GENDERS);
        model.put("castes", StudentOptionsGenerator.CASTES);
        model.put("communityLocations", StudentOptionsGenerator.COMMUNITY_LOCATIONS);
        model.put("religions", StudentOptionsGenerator.RELIGIONS);
        model.put("agesFrom", getAges());
        model.put("agesTo", getAges());
        model.put("talents", StudentOptionsGenerator.TALENTS);
    }

    @RequestMapping(value = "{id}/edit", method = GET)
    public String edit(@PathVariable String id,
                       @RequestParam(required = false, defaultValue = "") String noteUpdateStatus,
                       @RequestParam(required = false) boolean noteAddedSuccesfully,
                       Map<String, Object> model) {

        Student theStudent = service.load(id);

        model.put("classes", createDropDownList(StudentOptionsGenerator.STUDENT_CLASSES, theStudent.getStudentClass()));
        model.put("genders", createDropDownList(StudentOptionsGenerator.GENDERS, theStudent.getGender()));
        model.put("castes", createDropDownList(StudentOptionsGenerator.CASTES, theStudent.getCaste()));
        model.put("communityLocations", createDropDownList(StudentOptionsGenerator.COMMUNITY_LOCATIONS, theStudent.getCommunityLocation()));
        model.put("studentId", theStudent.getStudentId());
        model.put("name", theStudent.getName());
        model.put("dateOfBirth", theStudent.getDateOfBirthForDisplay());
        model.put("religions", createDropDownList(StudentOptionsGenerator.RELIGIONS, theStudent.getReligion()));
        model.put("subcastes", createDropDownList(StudentOptionsGenerator.SUBCASTES, theStudent.getSubCaste()));
        model.put("father", theStudent.getFather());
        model.put("mother", theStudent.getMother());
        model.put("talents", createCheckBoxList(StudentOptionsGenerator.TALENTS, theStudent.talentDescriptions()));

        model.put("noteUpdateStatus", noteUpdateStatus);
        model.put("noteAddedSuccesfully", noteAddedSuccesfully);

        return "students/edit";
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

    private static List<DropDownElement> createDropDownList(List<String> values, String selectedValue) {
        List<DropDownElement> dropDownElements = new ArrayList<DropDownElement>();
        for (String value : values) {
            dropDownElements.add(new DropDownElement(value, value.equals(selectedValue)));
        }
        return dropDownElements;
    }

    private static List<CheckBoxElement> createCheckBoxList(List<String> values, List<String> selectedValues) {
        List<CheckBoxElement> checkBoxElements = new ArrayList<CheckBoxElement>();
        for (String value : values) {
            checkBoxElements.add(new CheckBoxElement(value, selectedValues.contains(value)));
        }
        return checkBoxElements;
    }

    private static List<String> getAges() {
        List<String> ages = new ArrayList<String>();

        for (int age = AGES_FROM; age <= AGES_TO; age++) {
            ages.add(age + "");
        }

        return ages;
    }

    private static class DropDownElement {
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

    private static class CheckBoxElement {
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
