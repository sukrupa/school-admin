package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;
import static org.sukrupa.cucumber.SahiFacade.browser;
import org.sukrupa.cucumber.context.OnSukrupaHomepage;

public class SearchOnSukrupaSteps extends OnSukrupaHomepage {
    @When ("^I search for \"([^\"]*)\"$")
    public void addNewTalent(String searchString){
        browser().byId("searchInput").setValue(searchString);
    }
}