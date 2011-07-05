package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;
import net.sf.sahi.client.ElementStub;
import org.sukrupa.cucumber.context.Login;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class BigNeedSteps extends Login {
    private static final String TOP_LEVEL_DIV = "page";


    @Then("^a \"([^\"]+)\" costing \"([^\"]+)\" should be displayed")
    public void itemWithCostShouldBeDisplayed(String name, String cost) {
        assertTrue(itemWithCostExists(name, cost));
    }

    @Then("^a \"([^\"]+)\" costing \"([^\"]+)\" should not be displayed")
    public void itemWithCostShouldNotBeDisplayed(String name, String cost) {
        assertFalse(itemWithCostExists(name, cost));
    }

    @Then("^a \"([^\"]+)\" should not be displayed")
    public void itemShouldNotBeDisplayed(String name) {
        assertFalse(browser().cell(name).under(browser().cell("Name")).exists(true));
    }

    @When("^I delete the \"([^\"]+)\"")
    public void deleteItem(String itemName){
        ElementStub deleteButton = browser().submit("Delete").in(browser().cell(itemName).parentNode());
        assertTrue(deleteButton.exists(true));
        deleteButton.click();
    }

    private boolean itemWithCostExists(String name, String cost) {
        ElementStub nameCellWithCorrectName = browser().cell(name).under(browser().cell("Item"));
        ElementStub costCellWithCorrectCost = browser().cell(cost).under(browser().cell("Cost"));
        ElementStub rowContainingBothNameAndCost = browser().cell(cost).in(browser().cell(name).parentNode());
        return nameCellWithCorrectName.exists(true) && costCellWithCorrectCost.exists(true) && rowContainingBothNameAndCost.exists(true);
    }
}
