package org.sukrupa.app.needs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sukrupa.bigneeds.BigNeed;
import org.sukrupa.bigneeds.BigNeedRepository;
import org.sukrupa.platform.RequiredByFramework;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/bigneeds")
public class BigNeedsController {

    private BigNeedRepository bigNeedRepository;

    @RequiredByFramework
    public BigNeedsController() {
    }

    @Autowired
    public BigNeedsController(BigNeedRepository bigNeedRepository) {
        this.bigNeedRepository = bigNeedRepository;
    }

    @RequestMapping
    public String list(Map<String, Object> model, HttpSession session) {
        List<BigNeed> bigNeedList = bigNeedRepository.getList();
        int priority = bigNeedList.size() == 0 ? 1 : bigNeedList.get(bigNeedList.size() - 1).getPriority() + 1;
        model.put("message", session.getAttribute("message"));
        model.put("shouldDisplayMessage",session.getAttribute("message") != null);
        model.put("priority", priority);
        model.put("bigNeedList", bigNeedList);
        session.removeAttribute("message");
        return "bigNeeds/bigNeedsList";
    }

    @RequestMapping(value = "create", method = POST)
    @Transactional
    public String create(@RequestParam String priority, @RequestParam String itemName, @RequestParam String itemCost,HttpSession session, Map<String, Object> model) {
        bigNeedRepository.addNeed(new BigNeed(itemName, Double.parseDouble(itemCost), Integer.parseInt(priority)),Integer.parseInt(priority));
        session.setAttribute("message", "Added " + itemName);
        model.clear();
        return "redirect:/bigneeds";
    }

    @RequestMapping(value = "delete", method = POST)
    @Transactional
    public String delete(@RequestParam long itemId, HashMap<String, Object> model, HttpSession session) {
        BigNeed bigNeed = bigNeedRepository.getNeedById(itemId);
        this.bigNeedRepository.delete(bigNeed);
        session.setAttribute("message", "Deleted " + bigNeed.getItemName());
        model.clear();
        return "redirect:/bigneeds";
    }

    @RequestMapping(value = "saveeditedneed", method = POST)
    @Transactional
    public String saveEdit(@RequestParam String priority, @RequestParam long itemId, @RequestParam String itemName, @RequestParam String itemCost, HashMap<String, Object> model) {
        try {
            BigNeed bigNeed = bigNeedRepository.getNeedById(itemId);
            bigNeed.setItemName(itemName);
            bigNeed.setCost(Double.parseDouble(itemCost));
            bigNeedRepository.editNeed(bigNeed,Integer.parseInt(priority));
        } catch (Exception e) {
            return "Error: " + e.toString();
        }
        model.clear();
        return "redirect:/bigneeds";
    }
}