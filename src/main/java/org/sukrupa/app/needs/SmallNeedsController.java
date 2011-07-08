package org.sukrupa.app.needs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sukrupa.bigneeds.BigNeed;
import org.sukrupa.platform.RequiredByFramework;
import org.sukrupa.smallNeeds.SmallNeedRepository;

import java.io.PipedOutputStream;
import java.util.HashMap;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/smallneeds")
public class SmallNeedsController {

    @RequiredByFramework
    public SmallNeedsController() {

    }

    public SmallNeedsController(SmallNeedRepository smallNeedRepository) {
        //To change body of created methods use File | Settings | File Templates.
    }

    @RequestMapping
    public String list(HashMap<String, Object> model) {
        return "smallNeeds/list";
    }

     @RequestMapping(value="create" , method = POST)
    public String create(@RequestParam String priority,@RequestParam String schoolUniform,@RequestParam long cost, @RequestParam String comment, HashMap<String, Object> model) {

     model.put("message","Added Successfully");

        return "/smallNeeds/list";
    }
}
