package com.thoughtworks.blog.workflows;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class Login {

    private TwistSelenium selenium;

    public Login(TwistSelenium selenium) {
        this.selenium = selenium;
        selenium.open("/blog");
    }

    public void loginAsWith(String user, String pass) {
        selenium.type("j_username", user);
        selenium.type("j_password", pass);
        selenium.submit("loginForm");
        selenium.waitForPageToLoad("4000");
    }

    public void verifyThatTheHasLoggedIn(String user) {
        assertTrue(selenium.isTextPresent("Logged in as " + user));
        assertTrue(selenium.isTextPresent("Logout"));
    }

    public void logoutFromTheBlog() {
    	
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
        selenium.click("link=Logout");
        selenium.waitForPageToLoad("4000");
    }

    public void verifyThatTheHasLoggedOut(String user) {
        assertFalse(selenium.isTextPresent("Logged in as " + user));
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
