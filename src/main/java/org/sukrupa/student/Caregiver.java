package org.sukrupa.student;

import org.sukrupa.platform.DoNotRemove;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Caregiver {

    @Id
    @GeneratedValue
    private long id;

    @DoNotRemove
    Caregiver() {
    }

    private String name;
    private String education;

    public Caregiver(String name, String education) {
        this.name = name;
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducation() {
        return education;
    }
}
