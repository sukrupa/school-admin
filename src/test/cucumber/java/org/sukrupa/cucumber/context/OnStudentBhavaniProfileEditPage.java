package org.sukrupa.cucumber.context;

import cuke4duke.annotation.Before;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.sukrupa.cucumber.SahiFacade.browser;


public class OnStudentBhavaniProfileEditPage extends BasicWebSteps {

    private final String homePage = "http://localhost:8080/students/";

    @Before("@OnStudentBhavaniProfileEditPage")
    public void studentBhavaniProfileEditPage(){
        browser().navigateTo(homePage);
        browser().link("Bhavani").click();
        browser().submit("Edit").click();
    }
}
