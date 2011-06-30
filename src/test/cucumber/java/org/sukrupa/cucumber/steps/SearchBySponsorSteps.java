package org.sukrupa.cucumber.steps;


import cuke4duke.annotation.I18n.EN.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class SearchBySponsorSteps extends BasicWebSteps {


    @Given("I am on the Search By Sponsor page")
    public void navigateToSearchBySponsorPage() {
        browser().navigateTo("http://localhost:8080/students/searchbysponsor");
    }

    @When("^I enter sponsor name \"([^\"]*)\"$")
    public void enterSponsorName(String value) {
        browser().textbox("sponsorName").setValue(value);
    }
}
