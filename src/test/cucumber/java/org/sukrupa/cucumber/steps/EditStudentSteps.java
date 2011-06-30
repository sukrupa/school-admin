package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;
import org.sukrupa.cucumber.context.Login;
import org.sukrupa.cucumber.context.OnAddNewTalentPage;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;


public class EditStudentSteps {

    @When("^I edit student record of \"([^\"]*)\"$")
    public void gotoEditStudentPage(String studentName){
        browser().navigateTo(getConfigProperty("homepage")+"students/SK20090080");
        browser().submit("Edit").click();
    }

    @When("^I select the student record of \"([^\"]*)\"$")
    public void viewStudent(String studentName){
        browser().link(studentName).click();
    }

    @When("^I enter \"([^\"]*)\" name as \"([^\"]*)\"$")
    public void fillInTheNamefieldWith(String name, String fieldContent){
        browser().textbox(name+".name").setValue(fieldContent);
    }

    @When("^I enter \"([^\"]*)\" education as \"([^\"]*)\"$")
    public void fillInTheEducationfieldWith(String name, String fieldContent){
        browser().textbox(name+".education").setValue(fieldContent);
    }

    @When("^I enter \"([^\"]*)\" salary as \"([^\"]*)\"$")
    public void fillInTheSalaryfieldWith(String name, String fieldContent){
        browser().textbox(name+".salary").setValue(fieldContent);
    }

    @When("^I enter \"([^\"]*)\" contact as \"([^\"]*)\"$")
    public void fillInTheContactfieldWith(String name, String fieldContent){
        browser().textbox(name+".contact").setValue(fieldContent);
    }

    @When("^I select \"([^\"]*)\" as \"([^\"]*)\" occupation$")
    public void selectTheOccupationAs(String fieldContent, String name){
        browser().select(name+".occupation").choose(fieldContent);
    }

    @When("^I select \"([^\"]*)\" as \"([^\"]*)\" maritalStatus")
    public void selectTheMaritalStatusAs(String fieldContent, String name){
        browser().select(name+".maritalStatus").choose(fieldContent);
    }

    /*@When("^I select father occupation as \"([^\"]*)\"$")
    public void selectFatherOccupation(String studentName){
        browser().select("father.occupation")
    }*/
}
