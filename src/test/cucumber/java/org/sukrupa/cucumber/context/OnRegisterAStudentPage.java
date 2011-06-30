package org.sukrupa.cucumber.context;

import cuke4duke.annotation.Before;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class OnRegisterAStudentPage extends BasicWebSteps{
    @Before("@OnRegisterAStudentPage")
    public void registerAStudentPage(){
<<<<<<< HEAD
        browser().navigateTo(getConfigProperty("homepage") + "students/create");

=======
        browser().navigateTo("http://localhost:8080/students/create");
>>>>>>> nishi and aravind: almost finished add_student_record.feature
    }

}
