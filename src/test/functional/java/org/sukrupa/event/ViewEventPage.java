package org.sukrupa.event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sukrupa.student.Student;

import java.util.List;
import java.util.Set;

public class ViewEventPage {
    private WebDriver driver;

    public ViewEventPage(WebDriver driver, int eventId) {
        this.driver = driver;
        this.driver.get("http://localhost:8080/events/" + eventId);
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
        return null;//textFor("attendees");
    }

    private String textFor(String field) {
        return driver.findElement(By.xpath("//div[@class='"+ field +"']")).getText();
    }
}
