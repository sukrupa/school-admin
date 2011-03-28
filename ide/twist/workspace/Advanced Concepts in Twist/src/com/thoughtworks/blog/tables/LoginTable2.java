package com.thoughtworks.blog.tables;

import com.thoughtworks.blog.workflows.Login2;
import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class LoginTable2 {

    private final TwistSelenium selenium;

    public LoginTable2(TwistSelenium selenium) {
        this.selenium = selenium;
    }

    // Transient data, reset for each row.
    private Login2 loginFixture = null;
    private String username;

    /**
     * Initializer before each row in a table is invoked. Note that we intercept {@link #setUsername(String)} call to invoke this.
     */
    private void beginNewTest(String username) {
        loginFixture = new Login2(selenium);
        this.username = username;
    }

    public void setUsername(String username) {
        beginNewTest(username);
        loginFixture.setUserName(username);
    }

    public void setPassword(String password) {
        loginFixture.setPassword(password);
    }

    public boolean loggedIn() throws InterruptedException {
        loginFixture.submit();
        boolean loggedIn = loginFixture.isLoggedIn(username);

        if (loggedIn) {
            loginFixture.logoutFromTheBlog();
        }

        return loggedIn;
    }

}
