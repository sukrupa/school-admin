package org.sukrupa.app.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.student.StudentService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public AdminController(StudentService studentService) {
    }

    @RequestMapping()
    public String list(){
        return "admin/adminPage";
    }

    @RequestMapping(value="annualupdate", method= GET)
    public String annualUpdateBody(){
        return "admin/annualUpdate";
    }

    @RequestMapping(value="annualupdate", method= POST)
    public String performAnnualUpdate(){
        // updates all of the students
            // promote all the students
        // update complete
        return "redirect:annualupdate/success";
    }

    @RequestMapping(value = "annualupdate/success",  method=GET)
    public String annualUpdateSuccess() {
        return "admin/annualUpdateSuccess";
    }

}
