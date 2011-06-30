package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;
import org.sukrupa.cucumber.context.Login;
import org.sukrupa.cucumber.context.OnAddNewTalentPage;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;


public class EditStudentSteps {
    @When("^I select \"([^\"]*)\" to edit$")
    public void gotoEditStudentPage(String studentName){
        browser().link(studentName).click();
        browser().submit("Edit").click();

    }

     @When("^I select \"([^\"]*)\"$")
    public void viewStudent(String studentName){
        browser().link(studentName).click();
    }

    /*@When("^I select father occupation as \"([^\"]*)\"$")
    public void selectFatherOccupation(String studentName){
        browser().select("father.occupation")
    }*/
}
