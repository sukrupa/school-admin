package org.sukrupa.cucumber.context;

import cuke4duke.annotation.Before;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.sukrupa.cucumber.SahiFacade.browser;

public class OnRegisterAStudentPage extends Login{
    @Before("@OnRegisterAStudentPage")
    public void registerAStudentPage(){
        browser().navigateTo("http://localhost:8080/students/create");
       // super.login();
       //super.clickLink("Add New Student");
      // browser().link("Add New Student").click();
       // browser().click("Add New Student");
    }

}
