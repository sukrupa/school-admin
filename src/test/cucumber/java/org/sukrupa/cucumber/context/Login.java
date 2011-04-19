package org.sukrupa.cucumber.context;


import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.Given;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.sukrupa.cucumber.SahiFacade.browser;

public class Login extends BasicWebSteps{

    public void navigateTo() {
        browser().navigateTo("http://localhost:8080/students");
    }

    public void fillInTheUsernameWith(String username) {
        browser().textbox("j_username").setValue(username);
    }

    public void fillInThePasswordWith(String password) {
        browser().password("j_password").setValue(password);
    }

    @Before("@Login")
    public void login(){
        navigateTo();
        fillInTheUsernameWith("admin");
        fillInThePasswordWith("password");
        clickSubmitButton("Login");
    }
}
