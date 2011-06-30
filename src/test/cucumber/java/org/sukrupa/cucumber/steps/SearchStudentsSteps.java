package org.sukrupa.cucumber.steps;


import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;

import java.net.HttpCookie;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class SearchStudentsSteps extends BasicWebSteps {

    @Given("I am on the Student Search page")
    public void navigateTo() {
        browser().navigateTo(getConfigProperty("homepage")+"students/search");
        browser().byId("studentSearch").click();
    }

    @When("^I select the talent \"([^\"]*)\"$")
    public void selectTalent(String talent) {
        if (!talent.isEmpty()) {
            browser().select("availableTalents").choose(talent);
	        browser().button("addTalent").click();
        }
    }

    @When("^I enter name \"([^\"]*)\"$")
    public void fillInTheTextBoxWith(String value) {
        browser().textbox("name").setValue(value);
    }

    @When("^I select the age from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void selectAge(String ageFrom, String ageTo) {
        browser().select("ageFrom").choose(ageFrom);
        browser().select("ageTo").choose(ageTo);
    }

    @When("^I select class as \"([^\"]*)\"$")
    public void selectClass(String schoolClass) {
        browser().select("studentClass").choose(schoolClass);
    }

    @When("^I select gender as \"([^\"]*)\"$")
    public void selectGender(String gender) {
        browser().select("gender").choose(gender);
    }

    @When("^I select religion as \"([^\"]*)\"$")
    public void selectReligion(String religion) {
        browser().select("religion").choose(religion);
    }

    @When("^I select caste as \"([^\"]*)\"$")
    public void selectCaste(String caste) {
        browser().select("caste").choose(caste);
    }

    @When("^I select community location as \"([^\"]*)\"$")
    public void selectCommunityLocation(String communityLocatione) {
        browser().select("communityLocation").choose(communityLocatione);
    }

    @When("^I select family status as \"([^\"]*)\"$")
    public void selectFamilyStatus(String familyStatus) {
        browser().select("familyStatus").choose(familyStatus);
    }

}
