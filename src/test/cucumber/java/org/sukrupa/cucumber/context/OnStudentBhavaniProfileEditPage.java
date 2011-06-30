package org.sukrupa.cucumber.context;

import cuke4duke.annotation.Before;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;


public class OnStudentBhavaniProfileEditPage extends BasicWebSteps {


    @Before("@OnStudentBhavaniProfileEditPage")
    public void studentBhavaniProfileEditPage(){
        browser().navigateTo(getConfigProperty("homepage"));
        browser().link("Bhavani").click();
        browser().submit("Edit").click();
    }
}
