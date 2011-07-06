package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class CreateEventSteps extends BasicWebSteps {
    @When("^I enter the start time as ([^\"]*) pm$")
    public void enterStartTime(String eventStartTime) {
        browser().byId("startTime").setValue(eventStartTime);
        browser().radio("startTimePm").click();
    }

    @When("^I enter the end time as ([^\"]*) pm$")
    public void enterEndTime(String eventEndTime ) {
        browser().byId("endTime").setValue(eventEndTime);
        browser().radio("endTimePm").click();
    }

    @When("^I clear all the fields")
    public void clearFormFields(){
        browser().byId("clear").click();
    }

    @When("^I save the event")
    public void saveEvent(){
        browser().byId("save").click();
    }
}
