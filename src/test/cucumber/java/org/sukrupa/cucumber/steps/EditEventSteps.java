package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;

import static org.sukrupa.cucumber.SahiFacade.browser;


public class EditEventSteps extends BasicWebSteps{

    @When("^I Edit it")
    public void editEvent(){
        browser().submit("Edit").click();
    }

    @When("^I Save it")
    public void saveEvent(){
        browser().button("Save").click();
    }

}
