package org.sukrupa.app.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.student.StudentService;

import java.sql.Savepoint;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private StudentService studentService;

    @Autowired
    public AdminController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping()
    public String list(){
        return "admin/adminPage";
    }

    @RequestMapping(value="annualupdate", method= GET)
    public String annualUpdateBody( Map<String, Object> model){

        model.put("classUpdateDate",studentService.getLastClassUpdateDate());
        model.put("classAlreadyUpdated",studentService.classHasBeenUpdatedThisYear());

        return "admin/annualUpdate";
    }

    @RequestMapping(value="annualupdate/confirmation", method=GET)
    public String annualUpdateConfirmatioNBody(Map<String, Object> model){
        model.put("classUpdateDate",studentService.getLastClassUpdateDate());
        model.put("classAlreadyUpdated",studentService.classHasBeenUpdatedThisYear());
        return "admin/annualUpdateConfirmation";
    }


    @RequestMapping(value="annualupdate/confirmation", method= POST)
    public String performAnnualUpdate(){
        studentService.promoteStudentsToNextClass();
        return "redirect:/admin/annualupdate/success";

    }

    @RequestMapping(value = "annualupdate/success",  method=GET)
    public String annualUpdateSuccess() {
        return "admin/annualUpdateSuccess";
    }

}
