package org.sukrupa.app.needs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/bigneeds")
public class BigNeedsController {

    @RequestMapping
    public String list(Map <String, Object> model) {
        return "bigneeds/list";
    }


}
