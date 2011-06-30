package org.sukrupa.cucumber.context;

import cuke4duke.annotation.Before;

import static org.sukrupa.cucumber.SahiFacade.browser;

/**
 * Created by IntelliJ IDEA.
 * User: sreeraja
 * Date: 6/29/11
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class OnViewStudentPage {
    private final String homePage = "http://localhost:8080/students/";

    @Before("@OnAddNewTalentPage")
    public void addNewTalentPage(){
        browser().navigateTo(homePage);
        browser().link("View Students").click();
    }
}
