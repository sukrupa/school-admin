package org.sukrupa.cucumber.context;


import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.Given;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class Login extends BasicWebSteps{

    public void fillInTheUsernameWith(String username) {
        browser().textbox("j_username").setValue(username);
    }

    public void fillInThePasswordWith(String password) {
        browser().password("j_password").setValue(password);
    }

    @Before("@Login")
    public void login(){
        browser().navigateTo(getConfigProperty("homepage"));
        fillInTheUsernameWith("admin");
        fillInThePasswordWith("password");
        clickSubmitButton("Login");
    }
}
