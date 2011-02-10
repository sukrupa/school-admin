package org.sukrupa.app.event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateEventPage {
    private WebDriver driver;

    public CreateEventPage(WebDriver driver) {
        this.driver = driver;
    }

    public CreateEventPage navigateTo() {
        driver.get("http://localhost:8080/events/record");
        return this;
    }

    public CreateEventPage title(String title) {
        fillInText("title", title);
        return this;
    }

    public CreateEventPage date(String date) {
        fillInText("date", date);
        return this;
    }

    public CreateEventPage time(String time) {
        fillInText("time", time);
        return this;
    }

    public CreateEventPage venue(String venue) {
        fillInText("venue", venue);
        return this;
    }

    public CreateEventPage description(String description) {
        fillInTextArea("description", description);
        return this;
    }

    public CreateEventPage coordinator(String coordinator) {
        fillInText("coordinator", coordinator);
        return this;
    }

    public CreateEventPage attendees(String attendees) {
        fillInTextArea("attendees", attendees);
        return this;
    }

    public CreateEventPage notes(String notes) {
        fillInTextArea("notes", notes);
        return this;
    }

    public void save() {
        driver.findElement(By.xpath("//input[@value='Save']")).click();
    }

    private void fillInText(String field, String value) {
        driver.findElement(By.xpath("//input[@name='" + field + "']")).sendKeys(value);
    }

    private void fillInTextArea(String field, String value) {
        driver.findElement(By.xpath("//textarea[@name='" + field + "']")).sendKeys(value);
    }
}
