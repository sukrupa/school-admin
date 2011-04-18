package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.After;
import cuke4duke.annotation.I18n.EN.*;
import net.sf.sahi.client.Browser;
import org.sukrupa.cucumber.SahiFacade;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AuthenticationSteps {
    private SahiFacade sahiFacade = new SahiFacade();
    private Browser browser;

    @Given("I am on Sukrupa Page")
    public void navigateTo() {
        browser = sahiFacade.getBrowser();
        browser.navigateTo("http://localhost:8080/students");
    }

    @When("^I fill in the username with \"([^\"]*)\"$")
    public void fillInTheUsernameWith(String username) {
        browser.textbox("j_username").setValue(username);
    }

    @When("^I click \"([^\"]*)\" button$")
    public void clickButton(String buttonText){
        browser.submit(buttonText).click();
    }

    @When("^I fill in the password with \"([^\"]*)\"$")
    public void fillInThePasswordWith(String password) {
        browser.password("j_password").setValue(password);
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void shouldSee(String text) {
        assertTrue(browser.containsText(browser.div("page"), text));
    }

    @Given("^I am logged in")
    public void login(){
        navigateTo();
        fillInTheUsernameWith("admin");
        fillInThePasswordWith("password");
        clickButton("Login");
    }

    @When("^I click \"([^\"]*)\" link$")
    public void clickLink(String text){
        browser.link(text).click();
    }

    @After
    public void closeBrowser() {
        sahiFacade.closeBrowser();
    }
}
