package org.sukrupa.app.needs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sukrupa.bigneeds.BigNeed;
import org.sukrupa.platform.RequiredByFramework;
import org.sukrupa.smallNeeds.SmallNeed;
import org.sukrupa.smallNeeds.SmallNeedRepository;

import java.io.PipedOutputStream;
import java.util.HashMap;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/smallneeds")
public class SmallNeedsController {

    private final SmallNeedRepository smallNeedRepository;

    @Autowired
    public SmallNeedsController(SmallNeedRepository smallNeedRepository) {
        this.smallNeedRepository = smallNeedRepository;
    }

    @RequestMapping
    public String list(HashMap<String, Object> model) {
        model.put("smallNeedList", smallNeedRepository.getList());
        return "smallNeeds/list";
    }

    @RequestMapping(value = "create", method = POST)
    public String create(@RequestParam String priority, @RequestParam String schoolUniform, @RequestParam long cost, @RequestParam String comment, HashMap<String, Object> model) {

        model.put("message", "Added Successfully");

        return "/smallNeeds/list";
    }
}
