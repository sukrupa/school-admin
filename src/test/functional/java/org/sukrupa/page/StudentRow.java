package org.sukrupa.page;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StudentRow {
    private final WebElement row;

    public StudentRow(WebElement row) {
        this.row = row;
    }

    public String getName() {
        return row.findElement(By.xpath("td[@class='name']")).getText();
    }

    public String getStudentId() {
        return row.findElement(By.xpath("td[@class='studentId']")).getText();
    }

    public String getSex() {
        return row.findElement(By.xpath("td[@class='sex']")).getText();
    }

    public int getAge() {
        return Integer.parseInt(row.findElement(By.xpath("td[@class='age']")).getText());
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("name", getName()).append("studentId", getStudentId()).append("gender", getSex()).append("age", getAge()).toString();
    }
}
