package org.sukrupa.app.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin")
public class AdminController {

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
        return "redirect:annualupdate/success";
    }

    @RequestMapping(value = "annualupdate/success",  method=GET)
    public String annualUpdateSuccess() {
        return "admin/annualUpdateSuccess";
    }

}
