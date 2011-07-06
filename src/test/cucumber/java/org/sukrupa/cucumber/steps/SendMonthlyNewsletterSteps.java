package org.sukrupa.cucumber.steps;

import cuke4duke.annotation.I18n.EN.*;

import static org.sukrupa.cucumber.SahiFacade.browser;

public class SendMonthlyNewsletterSteps {
    @When("^I send the mail")
    public void sendMail(){
           browser().submit("send").click();
    }

}
