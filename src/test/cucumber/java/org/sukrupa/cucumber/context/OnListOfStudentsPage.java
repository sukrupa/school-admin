package org.sukrupa.cucumber.context;

import cuke4duke.annotation.Before;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class OnListOfStudentsPage extends BasicWebSteps {

    @Before("@OnListOfStudentsPage")
    public void listOfStudentsPage(){
        browser().navigateTo(getConfigProperty("homepage"));

    }
}
