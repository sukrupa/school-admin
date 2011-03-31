package org.sukrupa.base;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;
import org.sukrupa.platform.db.DatabaseHelper;

public abstract class FunctionalTestBase
{
    protected WebDriver driver = new HtmlUnitDriver();

    @Before
    public void setUp() throws Exception {
        driver.get("http://localhost:8080/students");
        driver.findElement(By.xpath("//*[@name='j_username']")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@name='j_password']")).sendKeys("password");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

}
