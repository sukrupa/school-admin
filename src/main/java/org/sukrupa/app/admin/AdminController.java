package org.sukrupa.app.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.student.StudentService;

import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private StudentService studentService;
    Map<String, Object> model=new HashMap<String, Object>();
    @Autowired
    public AdminController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping()
    public String list(){
        return "admin/adminPage";
    }

    @RequestMapping("/monthlyreports")
    public String monthlyReports(){
        model.put("student",)
        return "admin/monthlyreportsPage";
    }
}




