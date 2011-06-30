package org.sukrupa.cucumber.steps;
import cuke4duke.annotation.I18n.EN.*;
import org.sukrupa.cucumber.context.Login;
import org.sukrupa.cucumber.context.OnSukrupaHomepage;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;



public class SearchSteps extends OnSukrupaHomepage{
    @When("^I search for \"([^\"]*)\"$")
    public void addNewTalent(String searchTerm){
        browser().textbox("description").setValue(searchTerm);
    }
}
