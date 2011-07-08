package org.sukrupa.app.needs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sukrupa.smallNeeds.SmallNeed;
import org.sukrupa.smallNeeds.SmallNeedRepository;

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
        List<SmallNeed> smallNeedList = smallNeedRepository.getList();
        model.put("smallNeedList", smallNeedList);
        model.put("priority", smallNeedList.size() + 1);
        return "smallNeeds/smallNeedsList";
    }

    @RequestMapping(value = "create", method = POST)
    public String create(@RequestParam int priority, @RequestParam String itemName, @RequestParam long cost, @RequestParam String comment, HashMap<String, Object> model) {
        smallNeedRepository.put(new SmallNeed(itemName, cost, comment, priority));
        model.put("message", "Added Successfully");
        return "redirect:/smallneeds";
    }
}
