package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;
import net.sf.sahi.client.Browser;
import net.sf.sahi.client.ElementStub;
import org.sukrupa.cucumber.context.Login;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class BigNeedSteps extends Login {
    private static final String TOP_LEVEL_DIV = "page";

    @Given("^I am on the Big Needs page$")
    public void navigateTo() {
        browser().navigateTo(getConfigProperty("homepage"));
        browser().link("Big Needs").click();
    }

    @Then("^a \"([^\"]+)\" costing \"([^\"]+)\" should be displayed")
    public void itemWithCostShouldBeDisplayed(String itemName, String cost) {
        assertTrue(itemWithCostExists(itemName, cost));
    }

    @Then("^a \"([^\"]+)\" costing \"([^\"]+)\" should not be displayed")
    public void itemWithCostShouldNotBeDisplayed(String itemName, String cost) {
        assertFalse(itemWithCostExists(itemName, cost));
    }

    private boolean itemWithCostExists(String itemName, String cost) {
        ElementStub nameCellWithCorrectName = browser().cell(itemName).under(browser().cell("Item"));
        ElementStub costCellWithCorrectCost = browser().cell(cost).under(browser().cell("Cost"));
        ElementStub rowContainingBothNameAndCost = browser().cell(cost).in(browser().cell(itemName).parentNode());
        return nameCellWithCorrectName.exists(true) && costCellWithCorrectCost.exists(true) && rowContainingBothNameAndCost.exists(true);
    }
}
