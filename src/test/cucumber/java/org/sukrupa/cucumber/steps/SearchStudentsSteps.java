package org.sukrupa.cucumber.steps;


import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;

import java.net.HttpCookie;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class SearchStudentsSteps extends BasicWebSteps{

    @Given("I am on the Student Search page")
    public void navigateTo() {
        browser().navigateTo("http://localhost:8080/students/search");
    }

    @When("^I select the talent \"([^\"]*)\"$")
    public void selectTalent(String talent) {
        browser().select("availableTalents").choose(talent);
	    browser().button("addTalent").click();
    }

    @When("^I fill in the name with \"([^\"]*)\"$")
    public void fillInTheTextBoxWith(String value) {
        browser().textbox("name").setValue(value);
    }

    @When("^I select the age from \"([^\"]*)\"$")
    public void selectMinimumAge(String age) {
        browser().select("ageFrom").choose(age);
    }

    @When("^I select the age to \"([^\"]*)\"$")
    public void selectMaximumAge(String age) {
        browser().select("ageTo").choose(age);
    }

    @When("^I select class as \"([^\"]*)\"$")
    public void selectClass(String schoolClass) {
        browser().select("studentClass").choose(schoolClass);
    }

    @When("^I select gender as \"([^\"]*)\"$")
    public void selectGender(String gender) {
        browser().select("gender").choose(gender);
    }
}
