package org.sukrupa.app.event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.Set;

public class ViewEventPage {
    private WebDriver driver;

    public ViewEventPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return textFor("title");
    }

    public String getVenue() {
        return textFor("venue");
    }

    public String getDate() {
        return textFor("date");
    }

    public String getDay() {
        return textFor("day");
    }

    public String getTime() {
        return textFor("time");
    }

    public String getDescription() {
        return textFor("description");
    }

    public String getNotes() {
        return textFor("notes");
    }

    public Set<String> getAttendees() {
        Set<String> attendees = new HashSet<String>();
        for (WebElement element : driver.findElements(By.xpath("//*[@class='attendee']"))) {
            attendees.add(element.getText());
        }
        return attendees;
    }

    private String textFor(String field) {
        return driver.findElement(By.xpath("//*[@class='" + field + "']")).getText();
    }
}