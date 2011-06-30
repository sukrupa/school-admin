package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;

/**
 * Created by IntelliJ IDEA.
 * User: Thoughtworks
 * Date: 6/30/11
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditStudentProfileSteps extends BasicWebSteps{



     @When("^I choose \"([^\"]*)\" from student list$")
    public void chooseNameFromStudentList(String name){
        browser().link(name).click();
    }

      @When("^I enter \"([^\"]*)\" as the Sponsor")
    public void enterIntoTheSponsorTextBox(String text){
        browser().textbox("sponsored").setValue(text);
    }

    @Then("^\"([^\"]*)\" should be displayed in Sponsor")
    public void shouldBeDisplayedInSponsorTextBox(String text){
         String objectValue = browser().byId("sponsored").getValue();
        assertThat(objectValue, containsString(text));
    }

}
