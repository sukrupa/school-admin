package org.sukrupa.app.annualupdate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="/annualupdate")
public class AnnualUpdateController {

    @RequestMapping
    public String annualUpdate() {

        return "annualUpdate";
  }
}
