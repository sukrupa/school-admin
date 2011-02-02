package org.sukrupa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

public class StudentRow {
    private final WebElement row;

    public StudentRow(WebElement row) {
        this.row = row;
    }

    public String getName() {
        return row.findElement(By.xpath("//td[@class='name']")).getText();
    }

    public String getStudentId() {
        return row.findElement(By.xpath("//td[@class='studentId']")).getText();
    }

    public String getSex() {
        return row.findElement(By.xpath("//td[@class='sex']")).getText();
    }

    public String getAge() {
        return row.findElement(By.xpath("//td[@class='age']")).getText();
    }

    public Student getStudent() {
        return new StudentBuilder().name(getName()).studentId(getStudentId()).sex(getSex()).build();
    }
}
