package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class RegisterStudentSteps {
    @When("I register the student")
    public void registerStudent(){
        browser().submit("register").click();
    }
    @When("I cancel the student registration")
    public void cancelStudentRegistration(){
        browser().button("cancel").click();
    }
}
