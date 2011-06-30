package org.sukrupa.cucumber.context;

import cuke4duke.annotation.Before;
import static org.sukrupa.cucumber.CucumberFacade.getConfigProperty;
import static org.sukrupa.cucumber.SahiFacade.browser;

public class OnSukrupaHomepage {
    @Before("@OnSukrupaHomepage")
    public void searchSukrupa(){
        browser().navigateTo(getConfigProperty("sukrupa"));
    }
}
