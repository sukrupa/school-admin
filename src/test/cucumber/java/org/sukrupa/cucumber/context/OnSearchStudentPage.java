package org.sukrupa.cucumber.context;


import cuke4duke.annotation.Before;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.sukrupa.cucumber.SahiFacade.browser;

public class OnSearchStudentPage extends BasicWebSteps{

    @Before("@OnSearchStudentPage")
    public void searchStudentsPage(){
        browser().navigateTo("http://localhost:8080/students/search");
    }
    
}
