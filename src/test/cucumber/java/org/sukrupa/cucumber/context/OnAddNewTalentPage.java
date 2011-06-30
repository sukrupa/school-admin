package org.sukrupa.cucumber.context;

import cuke4duke.annotation.Before;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class OnAddNewTalentPage extends BasicWebSteps {

    @Before("@OnAddNewTalentPage")
    public void addNewTalentPage(){
        browser().navigateTo(getConfigProperty("homepage") + "admin/talents/new");
        //MAKE BELOW WORK!!!!
        //browser().link("Tools").click();
        //browser().link("Add New Talent").click();
    }


}