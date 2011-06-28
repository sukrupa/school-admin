package org.sukrupa.cucumber.context;

import cuke4duke.annotation.Before;
import org.sukrupa.cucumber.steps.BasicWebSteps;

import static org.sukrupa.cucumber.SahiFacade.browser;

/**
 * Created by IntelliJ IDEA.
 * User: Thoughtworks
 * Date: 6/28/11
 * Time: 4:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class OnAddNewTalentPage extends BasicWebSteps {

    private final String homePage = "http://localhost:8080/students/";

    @Before("@OnAddNewTalentPage")
    public void addNewTalentPage(){
        browser().navigateTo(homePage);
        browser().link("Tools").click();
        browser().link("Add New Talent").click();
    }
}