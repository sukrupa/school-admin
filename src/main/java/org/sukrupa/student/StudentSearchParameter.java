package org.sukrupa.student;

import org.sukrupa.platform.DoNotRemove;

public class StudentSearchParameter {

    public static final String WILDCARD_CHARACTER = "*";

    private String studentClass = WILDCARD_CHARACTER;
    private String gender = WILDCARD_CHARACTER;
    private String caste = WILDCARD_CHARACTER;
    private String communityLocation = WILDCARD_CHARACTER;
    private String ageFrom = WILDCARD_CHARACTER;
    private String ageTo = WILDCARD_CHARACTER;
    private Talent[] talents = new Talent[0];
    private String religion = WILDCARD_CHARACTER;

    public StudentSearchParameter(String studentClass, String gender, String caste, String communityLocation, String ageFrom, String ageTo, Talent[] talent, String religion) {
        this.studentClass = studentClass;
        this.gender = gender;
        this.caste = caste;
        this.communityLocation = communityLocation;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.talents = talent;
        this.religion = religion;
    }

    @DoNotRemove
    public StudentSearchParameter() {
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getCommunityLocation() {
        return communityLocation;
    }

    public void setCommunityLocation(String communityLocation) {
        this.communityLocation = communityLocation;
    }

    public String getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(String ageFrom) {
        this.ageFrom = ageFrom;
    }

    public String getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(String ageTo) {
        this.ageTo = ageTo;
    }

    public Talent[] getTalents() {
        return talents;
    }

    public void setTalents(Talent[] talents) {
        this.talents = talents;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

}
