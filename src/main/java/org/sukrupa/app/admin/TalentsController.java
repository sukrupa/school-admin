package org.sukrupa.app.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sukrupa.student.StudentService;

@Controller
@RequestMapping("/admin/talents")
public class TalentsController {


    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newTalent(){
        return "admin/talents/new";
    }
}
