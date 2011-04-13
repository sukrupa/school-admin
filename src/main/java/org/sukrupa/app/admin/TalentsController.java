package org.sukrupa.app.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sukrupa.app.admin.talents.TalentForm;
import org.sukrupa.app.admin.talents.TalentsService;

@Controller
@RequestMapping("/admin/talents")
public class TalentsController {


    private TalentsService talentsService;

    @Autowired
    public TalentsController(TalentsService talentsService) {
        this.talentsService = talentsService;
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newTalent(){
        return "admin/talents/new";
    }

    public String create(TalentForm talentForm) {
        this.talentsService.createTalent(talentForm) ;
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
