package org.sukrupa.app.needs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sukrupa.bigneeds.BigNeed;
import org.sukrupa.bigneeds.BigNeedRepository;
import org.sukrupa.platform.RequiredByFramework;

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
    public String list(Map<String, Object> model) {
        List<BigNeed> bigNeedList = bigNeedRepository.getList();
        model.put("bigNeedList", bigNeedList);
        return "bigNeeds/list";
    }

    @RequestMapping(value = "create", method = POST)
    public String create(@RequestParam String itemName, @RequestParam String cost, Map<String, Object> model) {
        model.put("message", "Added Successfully");
        bigNeedRepository.put(new BigNeed(itemName, Integer.parseInt(cost)));
        return "redirect:/bigneeds";
    }

    @RequestMapping(value = "{id}/delete", method = POST)
    @Transactional
    public String delete(@PathVariable long id, HashMap<String, Object> model) {
        BigNeed bigNeed = bigNeedRepository.getBigNeed(id);
        this.bigNeedRepository.delete(bigNeed);
        model.put("message", bigNeed.getItemName() + " was deleted");
        return "redirect:/bigneeds";
    }

    @RequestMapping(value = "/saveeditedneed", method = POST)
    @Transactional
    public String saveEdit(@RequestParam long itemId, @RequestParam String itemName, @RequestParam String itemCost, HashMap<String, Object> model) {
        try {
        BigNeed bigNeed = bigNeedRepository.getBigNeed(itemId);
        bigNeed.setItemName(itemName);
        bigNeed.setCost(Integer.parseInt(itemCost));

        model.put("message", "Edited Successfully");
        bigNeedRepository.put(bigNeed);


        } catch (Exception e) {
               

        }

        return "redirect:/bigneeds";
    }
}