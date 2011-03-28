package com.thoughtworks.blog.workflows;

import static junit.framework.Assert.assertTrue;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class BlogConfiguration {

    private TwistSelenium selenium;

    public BlogConfiguration(TwistSelenium selenium) {
        this.selenium = selenium;
    }

    public void goToConfigurationOptions() {
        selenium.click("link=Properties");
    }

    public void renameTheBlogNameToDescriptionToAndAboutBlurbTo(String name, String description, String about) {
        selenium.type("name", name);
        selenium.type("description", description);
        selenium.type("about", about);
        selenium.click("submit");
        selenium.waitForPageToLoad("4000");
    }

    public void verifyThatTheBlogNameIsAndDescriptionIsAndAboutBlurbIs(String name, String description, String about) {
        assertTrue(selenium.isTextPresent(name));
        assertTrue(selenium.isTextPresent(description));
        assertTrue(selenium.isTextPresent(about));
    }

}
