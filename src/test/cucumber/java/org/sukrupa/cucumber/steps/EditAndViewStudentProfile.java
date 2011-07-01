package org.sukrupa.cucumber.steps;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import net.sf.sahi.client.Browser;

import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class EditAndViewStudentProfile extends BasicWebSteps{

    public void isDisplayedUnderDivId(String text, String divIdName){
         assertTrue(browser().containsText(browser().div(divIdName), text));
    }

    public void isDisplayedUnderBasicInfoInStudentRecordPage(String text){
        isDisplayedUnderDivId(text, "basicInfo");
    }

    public void isDisplayedUnderSecondaryInfoInStudentRecordPage(String text){
        isDisplayedUnderDivId(text, "secondaryInfo");
    }

    @When("^I choose \"([^\"]*)\" from student list$")
    public void chooseNameFromStudentList(String name){
        clickLink(name);
    }

    @When("^I enter \"([^\"]*)\" as the Sponsor$")
    public void enterIntoTheSponsorTextBox(String text){
        fillInTheTextfieldWith("sponsored",text);
    }


    @Then("^\"([^\"]*)\" is displayed under Sponsor$")
    public  void  isDisplayedUnderSponsor(String text){
         isDisplayedUnderDivId(text, "sponsor");
    }


    @Then("^\"([^\"]*)\" is displayed under Academic Performance$")
    public  void  isDisplayedUnderAcademicPerformance(String text){
        isDisplayedUnderDivId(text, "student-performance");
    }

    @Then("^\"([^\"]*)\" is displayed under Disciplinary$")
    public  void  isDisplayedUnderDisciplinary(String text){
        isDisplayedUnderDivId(text, "student-disciplinary");
    }

    @Then("^\"([^\"]*)\" is displayed under Student Status$")
    public  void  isDisplayedUnderStudentStatus(String text){
        isDisplayedUnderSecondaryInfoInStudentRecordPage(text);
    }

    @Then("^\"([^\"]*)\" is displayed under Talents$")
    public  void  isDisplayedUnderTalents(String text){
        isDisplayedUnderDivId(text, "talents");
    }

      @Then("^\"([^\"]*)\" is displayed under Name$")
    public  void  isDisplayedUnderName(String text){
          isDisplayedUnderBasicInfoInStudentRecordPage(text);
    }

     @Then("^\"([^\"]*)\" is displayed under Gender$")
    public  void  isDisplayedUnderGender(String text){
        isDisplayedUnderBasicInfoInStudentRecordPage(text);
    }

     @Then("^\"([^\"]*)\" is displayed under Date Of Birth$")
    public  void  isDisplayedUnderDOB(String text){
          isDisplayedUnderBasicInfoInStudentRecordPage(text);
    }

     @Then("^\"([^\"]*)\" is displayed under Background$")
    public  void  isDisplayedUnderBackground(String text){
          isDisplayedUnderBasicInfoInStudentRecordPage(text);
    }

     @Then("^\"([^\"]*)\" is displayed under Events$")
    public  void  isDisplayedUnderEvents(String text){
          isDisplayedUnderBasicInfoInStudentRecordPage(text);
    }

    @When("^I enter \"([^\"]*)\" as Notes$")
    public void enterIntoTheTextArea(String text){
        enterAsThe(text, "new-note");
    }

    @When("^I \"([^\"]*)\" to the form$")
    public  void submitToTheForm(String submitButtonName){
        submitForm(submitButtonName);
    }

    @Then("^\"([^\"]*)\" should be displayed in the list of notes$")
    public  void  displayInTheListOfNotes(String text){
       shouldBeDisplayed(text);
    }

    @When("^I select \"([^\"]*)\" as the Student Status")
    public  void  selectStudentStatus(String text){
       choseFrom(text,"status");
    }

}
