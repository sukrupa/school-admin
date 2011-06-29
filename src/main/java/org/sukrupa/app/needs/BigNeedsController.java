package org.sukrupa.app.needs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.need.BigNeed;
import org.sukrupa.need.BigNeedService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bigneeds")
public class BigNeedsController {
    private BigNeedService bigNeedService;

    @Autowired
    public BigNeedsController(BigNeedService bigNeedService) {
        this.bigNeedService = bigNeedService;
    }

    @RequestMapping
    public String list(Map <String, Object> model) {
        return "bigneeds/list";
    }


}
