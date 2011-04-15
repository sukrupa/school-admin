package org.sukrupa.cucumber;

import cuke4duke.annotation.I18n.EN.Given;

import static org.junit.Assert.assertTrue;

public class BasicWebSteps {
    @Given("I am logged in")
    public void logIn() {
        assertTrue(true);
    }
}
