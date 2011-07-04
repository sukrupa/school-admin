package org.sukrupa.cucumber.steps;
import cuke4duke.annotation.I18n.EN.*;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;


public class AddNewTalentSteps {

    @When("^I enter talent description as ([^\"]*)$")
    public void enterTalent(String talentName) {
        browser().byId("description").setValue(talentName);
    }



}
