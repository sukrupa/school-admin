package org.sukrupa.cucumber.context;

import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;
import org.sukrupa.cucumber.context.Login;
import org.sukrupa.cucumber.context.OnAddNewTalentPage;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;


public class OnStudentMasterProfilePage extends BasicWebSteps {

    private final String homePage = "http://localhost:8080/students/";

    @Before("@OnStudentMasterProfilePage")
    public void studentMasterProfilePage(){
        browser().navigateTo(homePage);
        browser().link("Bhavani").click();
    }

}
