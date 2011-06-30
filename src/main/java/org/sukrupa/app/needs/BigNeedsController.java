package org.sukrupa.app.needs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.bigneeds.BigNeed;

import java.util.Map;

@Controller
@RequestMapping("/bigneeds")
public class BigNeedsController {

    @RequestMapping
    public String list(Map <String, Object> model) {
        model.put("bigNeedList",new BigNeed("Power Generator", 50000));
        return "bigneeds/list";
    }

}
