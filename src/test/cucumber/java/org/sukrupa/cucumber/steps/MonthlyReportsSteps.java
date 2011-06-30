package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;
import org.sukrupa.cucumber.context.Login;

import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class MonthlyReportsSteps extends Login {

    @When("^I select \"([^\"]*)\" in the sidebar")
    public void selectLinkInSidebar(String text) {
        browser().link(text).click();
    }

    @Given("^I am on the Monthly Reports Page$")
    public void iAmOnTheMonthlyReportsPage() {
        browser().navigateTo(getConfigProperty("homepage"));
        browser().link("Monthly Reports").click();
    }

    @Then("^student \"([^\"]*)\" should be displayed with sponsor \"([^\"]*)\"$")
    @Pending
    public void studentAnokShouldBeDisplayedWithSponsorTim_(String student, String sponsor) {

    }


}
