package org.sukrupa.student;

import org.sukrupa.platform.*;

import javax.persistence.*;

@Entity
public class Caregiver {

    @Id
    @GeneratedValue
    private long id;

    @RequiredByFramework
    Caregiver() {
    }

    public Caregiver(String name, String education, String occupation, String maritalStatus, String contact, String salary) {
        this.name = name;
        this.education = education;
        this.occupation = occupation;
        this.maritalStatus = maritalStatus;
        this.contact = contact;
        this.salary = salary;
    }

    private String name;
    private String education;

    private String occupation;

    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;

    @Column(name = "CONTACT_NUMBER")
    private String contact;

    private String salary;

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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
