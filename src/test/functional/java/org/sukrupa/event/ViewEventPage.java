package org.sukrupa.event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class ViewEventPage {
    private WebDriver driver;

    public ViewEventPage(WebDriver driver, int eventId) {
        this.driver = driver;
        this.driver.get("http://localhost:8080/events/" + eventId);
    }

    public String getTitle() {
        return driver.findElement(By.xpath("//div[@class='title']")).getText();
    }

    public String getVenue() {
        return driver.findElement(By.xpath("//div[@class='venue']")).getText();
    }
}
