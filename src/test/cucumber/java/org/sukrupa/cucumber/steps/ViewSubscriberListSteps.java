package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import net.sf.sahi.client.ElementStub;

import static junit.framework.Assert.assertFalse;
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

    @When("^I delete \"([^\"]*)\" with \"([^\"]*)\" as email from List of Subscribers$")
    public void deletingASubscriberFromSubscriberList(String subscriberName, String subscriberEmail) {
        deleteLinkContainingNameAndEmail(subscriberName, subscriberEmail);
    }

    @Then("^\"([^\"]*)\" with email \"([^\"]*)\" should not be displayed$")
    public void nameWithEmailIdNotDisplayed(String subscriberName, String subscriberEmail) {
        assertFalse(browser().cell(subscriberEmail).in(browser().cell(subscriberName).parentNode()).exists(true));
    }


    public void deleteLinkContainingNameAndEmail(String subscriberName, String subscriberEmail) {
        ElementStub rowContainingNameAndEmail = browser().cell(subscriberEmail).in(browser().cell(subscriberName).parentNode());
        ElementStub deleteLinkInNameAndEmail = browser().link("Delete").in((rowContainingNameAndEmail).parentNode());
        deleteLinkInNameAndEmail.click();
    }

}