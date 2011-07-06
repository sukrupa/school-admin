package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;
import org.sukrupa.cucumber.context.Login;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;


public class EditStudentSteps {

    @When("^I edit student record of ([^\"]*)$")
    public void gotoEditStudentPage(String studentName){
        browser().link(studentName).click();
        browser().submit("Edit").click();
    }

    @When("^I select the student record of ([^\"]*)$")
    public void viewStudent(String studentName){
        browser().link(studentName).click();
    }

    @When("^I enter ([^\"]*)'s name as ([^\"]*)$")
    public void fillInTheNamefieldWith(String name, String fieldContent){
        browser().textbox(name+".name").setValue(fieldContent);
    }

    @When("^I enter ([^\"]*)'s education as ([^\"]*)$")
    public void fillInTheEducationfieldWith(String name, String fieldContent){
        browser().textbox(name+".education").setValue(fieldContent);
    }

    @When("^I enter ([^\"]*)'s salary as ([^\"]*)$")
    public void fillInTheSalaryfieldWith(String name, String fieldContent){
        browser().textbox(name+".salary").setValue(fieldContent);
    }

    @When("^I enter ([^\"]*)'s contact as ([^\"]*)$")
    public void fillInTheContactfieldWith(String name, String fieldContent){
        browser().textbox(name+".contact").setValue(fieldContent);
    }

    @When("^I select ([^\"]*)'s occupation as ([^\"]*)$")
    public void selectTheOccupationAs(String name, String fieldContent){
        browser().select(name+".occupation").choose(fieldContent);
    }

    @When("^I select ([^\"]*)'s maritalStatus as ([^\"]*)$")
    public void selectTheMaritalStatusAs(String name, String fieldContent){
        browser().select(name+".maritalStatus").choose(fieldContent);
    }

    @When("^I save the changes$")
    public void iSaveTheChanges() {
        browser().byId("save").click();
    }


}
