package org.sukrupa.app.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.student.AnnualClassUpdateService;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin/annualupdate")
public class AnnualUpdateController {
    private AnnualClassUpdateService annualClassUpdateService;

    @Autowired
    public AnnualUpdateController(AnnualClassUpdateService annaulClassUpdateService) {
        this.annualClassUpdateService = annaulClassUpdateService;
    }

    @RequestMapping(method= GET)
    public String annualUpdateBody( Map<String, Object> model){

        model.put("classUpdateDate", annualClassUpdateService.getLastClassUpdateDate());
        model.put("classAlreadyUpdated", annualClassUpdateService.classHasBeenUpdatedThisYear());

        return "admin/annualupdate/annualUpdate";
    }

    @RequestMapping(value="confirmation", method=GET)
    public String annualUpdateConfirmatioNBody(Map<String, Object> model){
        model.put("classUpdateDate", annualClassUpdateService.getLastClassUpdateDate());
        model.put("classAlreadyUpdated", annualClassUpdateService.classHasBeenUpdatedThisYear());
        return "admin/annualupdate/annualUpdateConfirmation";
    }

    @RequestMapping(value="confirmation", method= POST)
    public String performAnnualUpdate(){
        annualClassUpdateService.promoteStudentsToNextClass();
        return "redirect:/admin/annualupdate/success";
    }

    @RequestMapping(value = "success",  method=GET)
    public String annualUpdateSuccess(Map<String, Object> model) {
        model.put("classUpdateCount", annualClassUpdateService.getClassUpdateCount());
        return "admin/annualupdate/annualUpdateSuccess";
    }

    @RequestMapping(value = "undoupdate", method = GET)
    public String undoAnnualUpdate(){
        annualClassUpdateService.undoPromoteStudentsToNextClass();
        return "admin/annualupdate/undoupdate";
    }
}
