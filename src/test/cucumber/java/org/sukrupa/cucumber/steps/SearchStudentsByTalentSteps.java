package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class SearchStudentsByTalentSteps extends BasicWebSteps {

    @When("^I Add the talent$")
    public void selectAddButton(){
        browser().button("addTalent").click();
    }

    @When("^I Remove the talent$")
    public void selectRemoveButton(){
        browser().button("removeTalent").click();
    }

    @When("^I Clear the chosen list of talents$")
    public void selectClearButton(){
        browser().button("clearTalents").click();
    }

    @When("^I select ([^\"]*) from list of talents$")
    public void selectedListOfTalents(String talent){
        browser().byId("availableTalents").choose(talent);

    }

    @Then("^Chosen list of talents should not contain \"([^\"]*)\"$")
    public void shouldNotBeInChosenListOfTalents(String talent){
         String objectValue = browser().byId("chosenTalents").getValue();
        assertThat(objectValue, not(containsString(talent)));
    }


}
