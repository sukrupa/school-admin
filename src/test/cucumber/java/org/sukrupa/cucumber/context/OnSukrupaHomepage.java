package org.sukrupa.cucumber.context;

import cuke4duke.annotation.Before;
import org.sukrupa.cucumber.steps.BasicWebSteps;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class OnSukrupaHomepage {

    private final String sukrupaHomepage= "http://www.sukrupa.org/";

    @Before("@OnSukrupaHomepage")
        public void addNewTalentPage(){
            browser().navigateTo(sukrupaHomepage);
        }
}