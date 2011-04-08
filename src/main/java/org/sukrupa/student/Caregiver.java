package org.sukrupa.student;

import org.sukrupa.platform.DoNotRemove;

import javax.persistence.Column;
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

    @Column(name = "CONTACT_NUMBER")
    private String contact;

    public Caregiver(String name, String education, String contact) {
        this.name = name;
        this.education = education;
        this.contact = contact;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
