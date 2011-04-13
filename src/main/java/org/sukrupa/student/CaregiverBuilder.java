package org.sukrupa.student;

/**
 * Created by IntelliJ IDEA.
 * User: cnoppani
 * Date: 12/04/2011
 * Time: 12:18
 * To change this template use File | Settings | File Templates.
 */
public class CaregiverBuilder {

    private String name;
    private String education;
    private String occupation;
    private String maritalStatus;
    private String contact;
    private String salary;


    public CaregiverBuilder name(String name)
    {
        this.name = name;
        return this;
    }

    public CaregiverBuilder education(String education)
    {
        this.education = education;
        return this;
    }

    public CaregiverBuilder occupation(String occupation)
    {
        this.occupation = occupation;
        return this;
    }

    public CaregiverBuilder maritalStatus(String maritalStatus)
    {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public CaregiverBuilder contact(String contact)
    {
        this.contact = contact;
        return this;
    }

    public CaregiverBuilder salary(String salary)
    {
        this.salary = salary;
        return this;
    }

    public Caregiver build()
    {
        return new Caregiver(name, education,occupation,maritalStatus,contact, salary);
    }
}
