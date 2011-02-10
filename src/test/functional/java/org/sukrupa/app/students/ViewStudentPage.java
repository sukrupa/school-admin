package org.sukrupa.app.students;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentPage {
    private WebDriver driver;

    public ViewStudentPage(WebDriver driver, String id) {
        this.driver = driver;
        driver.get("http://localhost:8080/students/" + id);
    }

    public String getStudentName() {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='value']"));
        WebElement nameElement = elements.get(1);
        return nameElement.getText();
    }

    public List<String> getNotes() {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='note']"));
        List<String> notes = new ArrayList<String>();
        for (WebElement element : elements) {
            notes.add(element.getText());
        }
        return notes;
    }
}
