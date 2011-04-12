package org.sukrupa.app.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.student.StudentService;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin/annualupdate")
public class AnnualUpdateController {
    private StudentService studentService;

    @Autowired
    public AnnualUpdateController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method= GET)
    public String annualUpdateBody( Map<String, Object> model){

        model.put("classUpdateDate",studentService.getLastClassUpdateDate());
        model.put("classAlreadyUpdated",studentService.classHasBeenUpdatedThisYear());

        return "admin/annualUpdate";
    }

    @RequestMapping(value="confirmation", method=GET)
    public String annualUpdateConfirmatioNBody(Map<String, Object> model){
        model.put("classUpdateDate",studentService.getLastClassUpdateDate());
        model.put("classAlreadyUpdated",studentService.classHasBeenUpdatedThisYear());
        return "admin/annualUpdateConfirmation";
    }

    @RequestMapping(value="confirmation", method= POST)
    public String performAnnualUpdate(){
        studentService.promoteStudentsToNextClass();
        return "redirect:/admin/annualupdate/success";
    }

    @RequestMapping(value = "success",  method=GET)
    public String annualUpdateSuccess(Map<String, Object> model) {
        model.put("classUpdateCount",studentService.getClassUpdateCount());
        return "admin/annualUpdateSuccess";
    }
}
