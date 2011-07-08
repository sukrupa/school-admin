package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;
import net.sf.sahi.client.ElementStub;
import org.springframework.beans.factory.annotation.Value;
import org.sukrupa.cucumber.context.Login;

import java.security.PublicKey;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class BigNeedSteps extends Login {
    private static final String TOP_LEVEL_DIV = "page";


    @Then("^a \"([^\"]+)\" costing \"([^\"]+)\" should be displayed with priority \"([^\"]+)\"")
    public void itemWithCostShouldBeDisplayed(String name, String cost, String priority) {
        assertTrue(itemWithCostExists(name, cost, priority));
    }

    @Then("^a \"([^\"]+)\" should be displayed as pre-populated \"([^\"]+)\"")
     public void shouldDisplayPrepopulatedPriority(String priority, String field) {
        assertTrue(browser().byId(field).getText().contains(priority));
    }

    @Then("^a \"([^\"]+)\" costing \"([^\"]+)\" with priority \"([^\"]+)\" should not be displayed")
    public void itemWithCostShouldNotBeDisplayed(String name, String cost, String priority) {
        assertFalse(itemWithCostExists(name, cost,priority));
    }

    @Then("^a \"([^\"]+)\" should not be displayed")
    public void itemShouldNotBeDisplayed(String name) {
        assertFalse(browser().cell(name).under(browser().cell("Name")).exists(true));
    }

    @When("^I delete the \"([^\"]+)\"")
    public void deleteItem(String itemName){
        ElementStub deleteButton = browser().button("Delete").in(browser().cell(itemName).parentNode());
        assertTrue(deleteButton.exists(true));
        deleteButton.click();
    }

    @When("^I edit the \"([^\"]+)\"")
    public void editItem(String itemName){
        ElementStub editButton = browser().button("Edit").in(browser().cell(itemName).parentNode());
        assertTrue(editButton.exists(true));
        editButton.click();
    }

    @When("^I add the need")
    public void addNeed(){
        browser().button("Add").click();
    }

    @When("^I save edited need")
    public void saveEditedNeed(){
        browser().button("Save").click();
    }

    @When("^I update item name \"([^\"]+)\" with \"([^\"]+)\"")
    public void updateItemName(String itemName, String newItemName){
        ElementStub getItem = browser().byXPath("//input[@value='" + itemName + "']");
        getItem.setValue(newItemName);
       // getItem.near(browser().cell("Cost")).setValue(cost);
    }

    @When("^I update cost of \"([^\"]+)\" from \"([^\"]+)\" to \"([^\"]+)\"")
    public void updateItemCost(String itemName, String cost){
        ElementStub getItem = browser().byXPath("//input[@value='" + itemName + "']").near(browser().cell("Cost"));
        getItem.setValue(cost);
       // getItem.near(browser().cell("Cost")).setValue(cost);
    }

    private boolean itemWithCostExists(String name, String cost,String priority) {
        ElementStub nameCellWithCorrectName = browser().cell(name).under(browser().cell("Item"));
        ElementStub costCellWithCorrectCost = browser().cell(cost).under(browser().cell("Cost"));
        ElementStub priorityCellWithCorrectCost = browser().cell(priority).under(browser().cell("Priority"));
        ElementStub rowContainingAllNameAndCostAndPriority = browser().cell(cost).in(browser().cell(name).in(browser().cell(priority).parentNode()));
        return nameCellWithCorrectName.exists(true) && costCellWithCorrectCost.exists(true) && priorityCellWithCorrectCost.exists(true); //&& rowContainingAllNameAndCostAndPriority.exists(true);
    }
}
