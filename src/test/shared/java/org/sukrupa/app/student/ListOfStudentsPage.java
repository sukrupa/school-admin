package org.sukrupa.app.student;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ListOfStudentsPage {

    private final WebDriver driver;

    public ListOfStudentsPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://localhost:8080/students");
    }

    public List<StudentRow> getStudents() {
        List<StudentRow> students = new ArrayList<StudentRow>();

        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='student']"));
        for (WebElement row : rows) {
            students.add(new StudentRow(row));
        }

        return students;
    }

}
