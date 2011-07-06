package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;

import static org.sukrupa.cucumber.SahiFacade.browser;


public class EditEventSteps {

    @When("^I Edit it")
    public void editEvent(){
        browser().submit("Edit").click();
    }
}
