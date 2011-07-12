package org.sukrupa.cucumber.steps;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;

import static junit.framework.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class ViewSubscriberListSteps {

@When("^I go to \"([^\"]*)\" in the sidebar$")
public void shouldGoToTools(String linkName) {
    browser().link(linkName).click();
}
    
@Then("^I should be able to \"([^\"]*)\"$")
public void shouldBeAbleToViewSubscribers(String linkName) {
    browser().link(linkName).click();
}

}
