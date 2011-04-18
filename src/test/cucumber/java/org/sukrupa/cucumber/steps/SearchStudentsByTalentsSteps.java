package org.sukrupa.cucumber.steps;


import cuke4duke.annotation.I18n.EN.*;
import net.sf.sahi.client.Browser;
import org.sukrupa.cucumber.SahiFacade;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class SearchStudentsByTalentsSteps {
    @Given("^I am on search students page$")
    public void searchStudentsPage(){
        browser().navigateTo("http://localhost:8080/students/search");
    }

    @When("^I select the talent \"([^\"]*)\"$")
    public void selectTalent(String talent) {
        browser().select("availableTalents").choose(talent);
	    browser().button("addTalent").click();
    }

    @Then("^I should not see \"([^\"]*)\"$")
    public void shouldNotSee(String text) {
        Browser browser = browser();
        assertFalse(browser.containsText(browser.div("page"), text));
    }
}
