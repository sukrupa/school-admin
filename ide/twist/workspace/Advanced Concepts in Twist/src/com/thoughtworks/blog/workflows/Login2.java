package com.thoughtworks.blog.workflows;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

/**
 * Fixture for logging into the application.
 */
public class Login2 {

    private TwistSelenium selenium;

    public Login2(TwistSelenium selenium) {
        this.selenium = selenium;
        selenium.open("/blog");
    }

    public void loginAsWith(String user, String pass) {
        setUserName(user);
        setPassword(pass);
        submit();
    }

    public void submit() {
        selenium.submit("loginForm");
        selenium.waitForPageToLoad("4000");
    }

    public void setPassword(String pass) {
        selenium.type("j_password", pass);
    }

    public void setUserName(String user) {
        selenium.type("j_username", user);
    }

    public void verifyThatTheHasLoggedIn(String user) {
        assertTrue(isLoggedIn(user));
        assertTrue(selenium.isTextPresent("Logout"));
    }

    public boolean isLoggedIn(String user) {
        return selenium.isTextPresent("Logged in as " + user);
    }

    public void logoutFromTheBlog() {
        selenium.click("link=Logout");
        selenium.waitForPageToLoad("4000");
    }

    public void verifyThatTheHasLoggedOut(String user) {
        assertFalse(isLoggedIn(user));
        assertFalse(selenium.isTextPresent("Logout"));
    }

    public void changePasswordTo(String newPassword) {
        selenium.click("link=Change password");
        selenium.type("password1", newPassword);
        selenium.type("password2", newPassword);
        selenium.click("//button[@value='Change Password']");
        selenium.waitForPageToLoad("4000");
    }

    public void verifyThatTheUserHasNotLoggedIn() throws InterruptedException {
        assertTrue(selenium.isTextPresent("An incorrect username/password was entered - please try again."));
        assertFalse(selenium.isTextPresent("Logout"));
    }

}
