package org.sukrupa.platform.webdriver;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.sukrupa.platform.webdriver.AuthenticatedHtmlUnitDriver.authenticatedDriver;

public class AuthenticatedHtmlUnitDriverFunctionalTest {

    @Test
    public void canMakeAnAuthenticatedRequest() {
        WebDriver driver = authenticatedDriver("admin", "password");

        driver.navigate().to("http://localhost:8080");

        assertThat(driver.getTitle(), is("List of Students"));
    }
}