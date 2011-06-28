package org.sukrupa.app.needs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.need.BigNeed;
import org.sukrupa.need.BigNeedService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/needs")
public class NeedsController {
    private BigNeedService bigNeedService;

    @Autowired
    public NeedsController(BigNeedService bigNeedService) {
        this.bigNeedService = bigNeedService;
    }

    @RequestMapping("bigNeeds")
    public void bigNeeds(){

    }

     @RequestMapping("/list")
    public void list(Map<String, List<BigNeed>> model)
    {
        //model.put("needs", bigNeedService.list());
    }

}
