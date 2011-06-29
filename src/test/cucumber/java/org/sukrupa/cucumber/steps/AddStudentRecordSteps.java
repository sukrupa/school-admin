package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;

import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;


public class AddStudentRecordSteps extends BasicWebSteps {

     private static final String TOP_LEVEL_DIV = "page";

    @When("^I click on \"([^\"]*)\"$")
    public void iClickOnAddNewStudent_(String text) {
        super.clickLink(text);
    }

    @Then("^I should see the \"([^\"]*)\" Page$")
    public void iShouldSeeTheRegisterAStudentPage(String text) {
        super.shouldSee(text);
    }


    @When("^I fill in the studentId with \"([^\"]*)\"$")
    public void iFillInTheStudentIdWithSK2010082011_(String text) {
        browser().textbox("studentId").setValue(text);
    }


    @When("^I fill in the name as \"([^\"]*)\"$")
    public void iFillInTheNameAsYael_(String text) {
        browser().textbox("name").setValue(text);

    }

    @When("^I fill in the dateOfBirth as \"([^\"]*)\"$")
    public void iFillInTheDateOfBirthAs06_03_1982_(String text) {
        browser().textbox("dateOfBirth").setValue(text);

    }

    @When("^I select the gender as \"([^\"]*)\"$")
    public void iSelectTheGenderAsFemale_(String text) {

        browser().select("gender").choose(text);
    }

    @When("^I click \"([^\"]*)\"$")
    public void iClickRegister_(String text) {
        super.clickSubmitButton(text);
    }

    @Then("^I should see the \"([^\"]*)\" page$")
    public void iShouldSeeTheEditStudentRecordYaelPage(String text) {
          super.shouldSee(text);
    }


}
