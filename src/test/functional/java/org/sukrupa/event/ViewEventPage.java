package org.sukrupa.event;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.sukrupa.app.students.StudentRow;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ViewEventPage {
    private WebDriver driver;

    public ViewEventPage() {
        this.driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/events/" + 1);
    }


    @Test
    @Ignore("[Rebecca, Suhas] WIP")
    public void testGetEvent(){
        String title = driver.findElements(By.xpath("//p[@class='title']")).get(0).getText();
        assertThat(title, is("Fake event_title"));
    }
}
