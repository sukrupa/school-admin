package org.sukrupa.app.needs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sukrupa.platform.RequiredByFramework;
import org.sukrupa.smallNeeds.SmallNeed;
import org.sukrupa.smallNeeds.SmallNeedRepository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/smallneeds")
public class SmallNeedsController {

    private SmallNeedRepository smallNeedRepository;

    @RequiredByFramework
    public SmallNeedsController(){
    }

    @Autowired
    public SmallNeedsController(SmallNeedRepository smallNeedRepository) {
        this.smallNeedRepository = smallNeedRepository;
    }

    @RequestMapping
    public String list(HashMap<String, Object> model, HttpSession session) {
        List<SmallNeed> smallNeedList = smallNeedRepository.getList();
        model.put("smallNeedList", smallNeedList);
        model.put("priority", smallNeedList.size() + 1);
        model.put("message", session.getAttribute("message"));
        model.put("shouldDisplayMessage",session.getAttribute("message") != null);
        session.removeAttribute("message");
        return "smallNeeds/smallNeedsList";
    }

    @RequestMapping(value = "create", method = POST)
    public String create(@RequestParam int priority, @RequestParam String itemName, @RequestParam String itemCost, @RequestParam String comment, HttpSession session,HashMap<String, Object> model) {
        smallNeedRepository.put(new SmallNeed(itemName, Double.parseDouble(itemCost), comment, priority));
        session.setAttribute("message", "Added " + itemName);
        return list(model,session);
    }

    @RequestMapping(value = "delete", method = POST)
    @Transactional
    public String delete(@RequestParam long itemId, HashMap<String, Object> model, HttpSession session) {
        SmallNeed smallNeed = smallNeedRepository.getSmallNeed(itemId);
        this.smallNeedRepository.delete(smallNeed);
        this.smallNeedRepository.getSmallNeed(itemId);
        session.setAttribute("message", "Deleted " + smallNeed.getItemName());
        return list(model,session);
    }
}
