package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;
import net.sf.sahi.client.Browser;

import static org.sukrupa.cucumber.SahiFacade.browser;


public class AdminNavigationSteps {

    @Given("^I am on the ([^\"]*) page$")
    public void navigateTo(String pageName) {
        browser().link(pageName).click();
    }
}
