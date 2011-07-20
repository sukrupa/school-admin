package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class EventSteps extends BasicWebSteps {
    @When("^I enter the start time as \"([^\"]*)\" pm$")
    public void enterStartTime(String eventStartTime) {
        browser().byId("startTime").setValue(eventStartTime);
        clickStartTimePM();
    }

    @When("^I enter the end time as \"([^\"]*)\" pm$")
    public void enterEndTime(String eventEndTime ) {
        browser().byId("endTime").setValue(eventEndTime);
        clickEndTimePM();
    }

    @When("^I click am for start time$")
    public void clickStartTimeAM() {
        browser().radio("startTimeAm").click();
    }

    @When("^I click pm for start time$")
    public void clickStartTimePM() {
        browser().radio("startTimePm").click();
    }

    @When("^I click am for end time$")
    public void clickEndTimeAM() {
        browser().radio("endTimeAm").click();
    }

    @When("^I click pm for end time$")
    public void clickEndTimePM() {
        browser().radio("endTimePm").click();
    }

    @When("^I add ([^\"]*) as an attendee$")
    public void enterIntoTheTextBox(String attendee) {
        browser().select("availableStudents").choose(attendee);
        browser().byId("addStudents").click();
    }

    @When("^I remove ([^\"]*) as an attendee$")
    public void removeFromTheTextBox(String attendee) {
        browser().select("attendingStudents").choose(attendee);
        browser().byId("removeStudents").click();
    }

    @When("^I clear all the fields")
    public void clearFormFields(){
        browser().byId("clear").click();
    }

    @When("^I clear all the students")
    public void clearFormStudents(){
        browser().byId("clearStudents").click();
    }

    @When("^I save the event")
    public void saveEvent(){
        browser().byId("save").click();
    }

    @When("^I Edit it")
    public void editEvent(){
        browser().submit("Edit").click();
    }

    @When("^I cancel it")
    public void cancelEvent(){
        browser().button("Cancel").click();
    }

}
