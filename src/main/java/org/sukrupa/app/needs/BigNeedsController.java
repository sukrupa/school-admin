package org.sukrupa.app.needs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.bigneeds.BigNeed;
import org.sukrupa.bigneeds.BigNeedRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/bigneeds")
public class BigNeedsController {

    private BigNeedRepository bigNeedRepository;

    @Autowired
    public BigNeedsController(BigNeedRepository bigNeedRepository){
        this.bigNeedRepository = bigNeedRepository;
    }

    @RequestMapping
    public String list(Map <String, Object> model) {
        List<BigNeed> bigNeedList = bigNeedRepository.getList();
        model.put("bigNeedList",bigNeedList);
        return "bigneeds/list";
    }

    @RequestMapping(value = "create", method = POST)
    public String create( @ModelAttribute("createBigNeed")BigNeed bigNeed, Map<String, Object> model) {
        bigNeedRepository.put(bigNeed);
        model.put("message", "Added Successfully");
       return list(model);
    }

}
