package org.sukrupa.cucumber.context;


import cuke4duke.annotation.Before;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class OnSearchStudentPage extends BasicWebSteps{

    @Before("@OnSearchStudentPage")
    public void searchStudentsPage(){
        browser().navigateTo(getConfigProperty("homepage")+"students/search");
    }
    
}
