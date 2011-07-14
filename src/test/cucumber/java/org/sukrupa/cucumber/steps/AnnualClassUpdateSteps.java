package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.When;

import static org.sukrupa.cucumber.SahiFacade.browser;

public class AnnualClassUpdateSteps extends BasicWebSteps{
    @When("^I update students$")
    public void updateStudents() {
        browser().submit("Update students").click();
    }
    @When("^I select No$")
    public void selectNo() {
        browser().button("No").click();
    }
    @When("^I select Yes$")
    public void selectYes() {
        browser().submit("Yes").click();
    }
    @When("^I navigate to ([^\"]*) profile$")
    public void navigateToLink(String name) {
        browser().link(name).click();
    }
}
