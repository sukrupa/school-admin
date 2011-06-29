package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;
import org.sukrupa.cucumber.context.Login;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class AuthenticationSteps extends Login{

    @Given("I am on Sukrupa Page")
    public void navigateTo() {
        browser().navigateTo(getConfigProperty("homepage"));
    }

    @When("^I enter the username \"([^\"]*)\"$")
    public void fillInTheUsernameWith(String username) {
       super.fillInTheUsernameWith(username);
    }

    @When("^I enter the password \"([^\"]*)\"$")
    public void fillInThePasswordWith(String password) {
        super.fillInThePasswordWith(password);
    }

    @When("^I logout")
    public void logout(){
        browser().link("Logout").click();
    }
}