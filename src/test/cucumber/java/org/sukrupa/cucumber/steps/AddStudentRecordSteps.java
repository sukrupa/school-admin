package org.sukrupa.cucumber.steps;

import net.sf.sahi.client.Browser;
import org.sukrupa.cucumber.context.OnListOfStudentsPage;
import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;

import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;


public class AddStudentRecordSteps extends BasicWebSteps {

     private static final String TOP_LEVEL_DIV = "page";

    @When("^I click on \"([^\"]*)\"$")
    public void iClickOnAddNewStudent_(String arg1) {
        super.clickLink(arg1);
    }

    @Then("^I should see the \"([^\"]*)\" Page$")
    public void iShouldSeeTheRegisterAStudentPage(String arg1) {
        super.shouldSee(arg1);
    }

    @When("^I fill in the student id with \"([^\"]*)\"$")
    public void iFillInTheStudentIdWithSK2010082011_(String arg1) {
        browser().textbox("studentId").setValue(arg1);
    }

    @When("^I fill in the name as \"([^\"]*)\"$")
    public void iFillInTheNameAsYael_(String arg1) {
        browser().textbox("name").setValue(arg1);

    }

    @When("^I fill in the dateOfBirth as \"([^\"]*)\"$")
    public void iFillInTheDateOfBirthAs06_03_1982_(String arg1) {
        browser().textbox("dateOfBirth").setValue(arg1);

    }

    @When("^I select the gender as \"([^\"]*)\"$")
    public void iSelectTheGenderAsFemale_(String arg1) {

        browser().select("gender").choose(arg1);
    }

    @When("^I click \"([^\"]*)\"$")
    public void iClickRegister_(String arg1) {
        super.clickSubmitButton(arg1);
    }

    @Then("^I should see the \"([^\"]*)\" page$")
    public void iShouldSeeTheEditStudentRecordYaelPage(String arg1) {
          super.shouldSee(arg1);
    }


}
