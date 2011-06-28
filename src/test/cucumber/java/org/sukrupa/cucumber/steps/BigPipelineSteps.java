package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;
import net.sf.sahi.client.Browser;
import org.sukrupa.cucumber.context.Login;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class BigPipelineSteps extends Login{
    private static final String TOP_LEVEL_DIV = "page";

    @Given("I am on Sukrupa Page")
    public void navigateTo() {
        super.navigateTo();
    }

    @When("^I click \"([^\"]*)\" link$")
    public void clickLink(String text){
        browser().link(text).click();
    }

     @Then("^I am taken to \"([^\"]*)\" page$")
     public void shouldSee(String text) {
        Browser browser = browser();
        assertTrue(browser.containsText(browser.div(TOP_LEVEL_DIV), text));
    }


}
