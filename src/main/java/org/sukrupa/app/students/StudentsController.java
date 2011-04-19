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
import java.util.List;
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

        List<String> validCriteria = searchParam.getValidCriteria();

        if (students.getStudents().isEmpty()) {
            model.put("searchCriteria", validCriteria);
            return "students/listEmpty";
        }

        model.put("page", students);

        model.put("searchCriteria", validCriteria);


        return "students/list";
    }

    @RequestMapping("search")
    public void search(Map<String, Object> model) {
        model.put("formhelper", studentService.getStudentReferenceData());
    }

    @RequestMapping(value = "{id}/edit", method = GET)
    public String edit(@PathVariable String id,
                       @RequestParam(required = false, defaultValue = "") String noteUpdateStatus,
                       @RequestParam(required = false) boolean noteAddedSuccesfully,
                       Map<String, Object> model) {

        Student student = studentService.load(id);
        setStudentData(student, noteUpdateStatus, noteAddedSuccesfully, model);
        return "students/edit";
    }

    private void setStudentData(Student student, String noteUpdateStatus, boolean noteAddedSuccesfully, Map<String, Object> model) {
        model.put("student", student);
        model.put("formhelper", present(student));
        model.put("noteUpdateStatus", noteUpdateStatus);
        model.put("noteAddedSuccesfully", noteAddedSuccesfully);
    }


    private StudentFormPresenter present(Student student) {
        return new StudentFormPresenter(student, new StudentReferenceData(studentService.getTalentRepository()));
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
                    case EXISTING_STUDENT:
                        model.put("statusType", "existing");
                        break;
                    case DROPOUT:
                        model.put("statusType", "dropout");
                        break;
                    case ALUMNI:
                        model.put("statusType", "alumni");
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
            @ModelAttribute("createStudent") StudentForm studentParam, Map<String, Object> model) {
        Errors errors = new BeanPropertyBindingResult(studentParam, "StudentForm");
        studentValidator.validate(studentParam, errors);

        if (mandatoryFieldsExist(errors)) {
            Student student = studentService.create(studentParam);
            return format("redirect:/students/%s/edit", student.getStudentId());
        } else {
            model.put("student", studentParam);
            model.put("errors", errors);

            addErrorToFields(model, errors);
            model.put("formhelper", present(Student.EMPTY_STUDENT));
            return "students/create";
        }
    }



    @RequestMapping(value = "{id}", method = POST)
    public String update(
            @PathVariable String id,
            @ModelAttribute("updateStudent") StudentForm studentForm,
            Map<String, Object> model) {

        Student student = studentService.load(id);

        Errors errors = new BeanPropertyBindingResult(studentForm, "StudentForm");
        studentValidator.validateImage(studentForm, errors);


        if(mandatoryFieldsExist(errors)){
            Student updatedStudent = studentService.update(studentForm);
            if (updatedStudent != null) {
                model.put("studentUpdatedSuccesfully", true);
                return format("redirect:/students/%s", id);
            } else {
                model.put("message", "Error updating student");
                return format("redirect:/students/%s/edit", id);
            }
        }else{
            model.put("errors", errors);
            addErrorToFields(model, errors);
            model.put("message", "Error updating student");
            studentService.setFormDataOnStudent(studentForm, student);
            setStudentData(student,"",false,model);
            return "students/edit";
        }
    }

    @RequestMapping(value = "create", method = GET)
    public String newStudent(HashMap<String, Object> model) {
        model.put("formhelper", present(Student.EMPTY_STUDENT));
        return "students/create";
    }

    private boolean mandatoryFieldsExist(Errors errors) {
        return errors.getErrorCount() == 0;
    }

    private void addErrorToFields(Map<String, Object> model, Errors errors) {
        for (FieldError error : errors.getFieldErrors()) {
            model.put(format("%sError", error.getField()), error.getDefaultMessage());
        }

    }

}