package org.sukrupa.student;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

public class StudentListPage {
    private List<Student> students;
    private int pageNumber;
    private int maxPageNumber;

    public StudentListPage(List<Student> students, int pageNumber, int maxPageNumber) {
        this.students = students;
        this.pageNumber = pageNumber;
        this.maxPageNumber = maxPageNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public boolean isPreviousEnabled() {
        return pageNumber > 1;
    }

    public int getNextPage() {
        return pageNumber + 1;
    }

    public int getPreviousPage() {
        return pageNumber - 1;
    }

    public boolean isNextEnabled() {
        return pageNumber < maxPageNumber;
    }
}
