package com.thoughtworks.blog.workflows;

import static junit.framework.Assert.assertTrue;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class AtTheHomePage {

    private TwistSelenium selenium;

    public AtTheHomePage(TwistSelenium selenium) {
        this.selenium = selenium;
        selenium.open("/blog");
    }

    public void verifyThatTheBlogTitleIs(String text) {
        assertTrue(selenium.isTextPresent(text));
    }

}
