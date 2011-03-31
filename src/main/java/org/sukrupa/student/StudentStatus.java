package org.sukrupa.student;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum StudentStatus {
    NOT_SET(0, "Not Set"), INACTIVE(1, "Inactive Student"), ACTIVE(2, "Active Student"), ALUMNI(3, "Alumni") ;

    private String name;
    private int id;

    StudentStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
