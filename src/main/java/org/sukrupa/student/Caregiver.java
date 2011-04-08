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

    private String occupation;

    @Column( name = "MARITAL_STATUS")
    private String maritalStatus;

    @Column(name = "CONTACT_NUMBER")
    private String contact;

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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
