package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;

import static org.sukrupa.cucumber.SahiFacade.browser;

/**
 * Created by IntelliJ IDEA.
 * User: mchamber
 * Date: 28/06/11
 * Time: 9:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class SearchStudentsByName extends BasicWebSteps {

    @When("^I fill in the name with \"([^\"]*)\"$")
    public void fillInTheTextBoxWith(String value) {
        browser().textbox("name").setValue(value);
    }

}
