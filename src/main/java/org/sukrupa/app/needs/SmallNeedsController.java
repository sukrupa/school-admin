package org.sukrupa.app.needs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.bigneeds.BigNeed;
import org.sukrupa.platform.RequiredByFramework;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/smallneeds")
public class SmallNeedsController {

    @RequiredByFramework
    public SmallNeedsController() {

    }

    @RequestMapping
    public String list() {
        return "smallNeeds/list";
    }



}
