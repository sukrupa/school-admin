package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;
import org.sukrupa.cucumber.context.Login;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class AuthenticationSteps extends Login{

    @Given("I am on Sukrupa Page")
    public void navigateTo() {
        super.navigateTo();
    }

    @When("^I fill in the username with \"([^\"]*)\"$")
    public void fillInTheUsernameWith(String username) {
       super.fillInTheUsernameWith(username);
    }

    @When("^I fill in the password with \"([^\"]*)\"$")
    public void fillInThePasswordWith(String password) {
        super.fillInThePasswordWith(password);
    }
}