package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;


public class EditStudentProfileSteps extends BasicWebSteps{


    @When("^I choose \"([^\"]*)\" from student list$")
    public void chooseNameFromStudentList(String name){
        browser().link(name).click();
    }

    @When("^I enter \"([^\"]*)\" as the Sponsor$")
    public void enterIntoTheSponsorTextBox(String text){
        browser().textbox("sponsored").setValue(text);
    }

    @Then("^\"([^\"]*)\" is displayed under Sponsor$")
    public  void  isDisplayedUnderSponsor(String text){
        assertTrue(browser().containsText(browser().div("sponsor"), text));
    }


    @Then("^\"([^\"]*)\" is displayed under Academic Performance$")
    public  void  isDisplayedUnderAcademicPerformance(String text){
        assertTrue(browser().containsText(browser().div("student-performance"), text));
    }

    @Then("^\"([^\"]*)\" is displayed under Disciplinary$")
    public  void  isDisplayedUnderDisciplinary(String text){
        assertTrue(browser().containsText(browser().div("student-disciplinary"), text));
    }

    @Then("^\"([^\"]*)\" is displayed under Student Status$")
    public  void  isDisplayedUnderStudentStatus(String text){
        assertTrue(browser().containsText(browser().div("secondaryInfo"), text));
    }

    @When("^I enter \"([^\"]*)\" as Notes$")
    public void enterIntoTheTextArea(String text){
        enterAsThe(text,"new-note");
    }

    @When("^I \"([^\"]*)\" to the form$")
    public  void submitToTheForm(String submitButtonName){
        submitForm(submitButtonName);
    }

    @Then("^\"([^\"]*)\" should be displayed in the list of notes$")
    public  void  displayInTheListOfNotes(String text){
       shouldBeDisplayed(text);
    }

}
