package com.thoughtworks.blog.contexts;

import com.thoughtworks.blog.workflows.Login;
import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class LoginAsWith {

    private final Login login;

    public LoginAsWith(TwistSelenium selenium) {
        this.login = new Login(selenium);

    }

    public void setUp(String user, String pass) {
        login.loginAsWith(user, pass);
    }

    public void tearDown(String user, String pass) {
        login.logoutFromTheBlog();
    }

}
