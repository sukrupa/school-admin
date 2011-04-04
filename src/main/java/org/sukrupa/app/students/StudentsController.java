package org.sukrupa.app.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sukrupa.student.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/students")
public class StudentsController {

    private StudentService studentService;
    private StudentValidator studentValidator;

    @Autowired
    public StudentsController(StudentService studentService, StudentValidator studentValidator) {
        this.studentService = studentService;
        this.studentValidator = studentValidator;
    }

    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1", value = "page") int pageNumber,
                       @ModelAttribute("searchParam") StudentSearchParameter searchParam,
                       Map<String, Object> model, HttpServletRequest request) {

        StudentListPage students = studentService.getPage(searchParam, pageNumber, request.getQueryString());

        if (students.getStudents().isEmpty()) {
            return "students/listEmpty";
        }

        model.put("page", students);

        return "students/list";
    }


    // @RequestMapping(value = "promote" , method = POST) feature not available yet
    public String promoteClass(Map<String, Integer> model) {
        int promoteStudentsCount = studentService.promoteStudentsToNextClass();
        model.put("numberOfStudentsUpdated", promoteStudentsCount);
        return "redirect:/students/update-successful";
    }

    @RequestMapping(value = "update-successful")
    public String promoteUpdateSuccessful(@RequestParam("numberOfStudentsUpdated") int promoteStudentCount, Map<String, Integer> model) {

        model.put("numberOfStudentsUpdated", promoteStudentCount);
        return "students/updateSuccessful";
    }

    @RequestMapping(value = "search")
    public void search(Map<String, Object> model) {
        model.put("formhelper", studentService.getReferenceData());
    }

    @RequestMapping(value = "{id}/edit", method = GET)
    public String edit(@PathVariable String id,
                       @RequestParam(required = false, defaultValue = "") String noteUpdateStatus,
                       @RequestParam(required = false) boolean noteAddedSuccesfully,
                       Map<String, Object> model) {


        Student student = studentService.load(id);

        student.getStudentId();
        model.put("student", student);
        model.put("formhelper", formHelperFor(student));
        model.put("noteUpdateStatus", noteUpdateStatus);
        model.put("noteAddedSuccesfully", noteAddedSuccesfully);

        return "students/edit";
    }


    private StudentEditFormHelper formHelperFor(Student theStudent) {
        return new StudentEditFormHelper(theStudent, studentService.getReferenceData());
    }

    @RequestMapping(value = "{id}", method = GET)
    public String view(@PathVariable String id,
                       @RequestParam(required = false) boolean studentUpdatedSuccesfully,
                       Map<String, Object> model) {
        Student student = studentService.load(id);
        if (student != null) {
            model.put("student", student);
            model.put("studentUpdatedSuccesfully", studentUpdatedSuccesfully);

            if (student.getStatus() == null)
                model.put("statusType", "default");
            else {
                switch (student.getStatus()) {
                    case ACTIVE:
                        model.put("statusType", "existing");
                        break;
                    case INACTIVE:
                        model.put("statusType", "dropout");
                        break;
                    default:
                        model.put("statusType", "default");
                        break;
                }
            }


            return "students/view";
        }

        return "students/viewFailed";
    }

    @RequestMapping(value = "create", method = POST)
    public String create(
            @ModelAttribute("createStudent") StudentProfileForm studentParam, Map<String, Object> model) {
        Errors errors = new BeanPropertyBindingResult(studentParam, "StudentProfileForm");
        studentValidator.validate(studentParam, errors);

        if (mandatoryFieldsExist(errors)) {
            Student student = studentService.create(studentParam);
            return format("redirect:/students/%s", student.getStudentId());
        } else {
            model.put("student", studentParam);
            model.put("errors", errors);

            addErrorToFieldIfNecessary("name", model, errors);
            addErrorToFieldIfNecessary("dateOfBirth", model, errors);
            addErrorToFieldIfNecessary("studentId", model, errors);
            model.put("formhelper", formHelperFor(Student.EMPTY_STUDENT));
            return "students/create";
        }
    }

    @RequestMapping(value = "{id}", method = POST)
    public String update(
            @PathVariable String id,
            @ModelAttribute("updateStudent") StudentProfileForm studentParam,
            Map<String, Object> model) {

        Student updatedStudent = studentService.update(studentParam);

        if (updatedStudent != null) {
            model.put("student", updatedStudent);
            model.put("studentUpdatedSuccesfully", true);
            return format("redirect:/students/%s", id);
        } else {
            model.put("message", "Error updating student");
            return format("redirect:/students/%s/edit", id);
        }
    }

    @RequestMapping(value = "create", method = GET)
    public String newStudent(HashMap<String, Object> model) {
        model.put("formhelper", formHelperFor(Student.EMPTY_STUDENT));
        return "students/create";
    }

    private boolean mandatoryFieldsExist(Errors errors) {
        return errors.getErrorCount() == 0;
    }

    private void addErrorToFieldIfNecessary(String name, Map<String, Object> model, Errors errors) {
        FieldError nameError = errors.getFieldError(name);
        model.put(format("%sError", name), no(nameError) ? null : nameError.getDefaultMessage());

    }

    private boolean no(FieldError nameError) {
        return nameError == null;
    }
}
