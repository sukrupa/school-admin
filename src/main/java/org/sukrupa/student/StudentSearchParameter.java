package org.sukrupa.student;

import org.sukrupa.platform.RequiredByFramework;

import java.util.*;

public class StudentSearchParameter {

    public static final String WILDCARD_CHARACTER = "*";

    private String name = WILDCARD_CHARACTER;
    private String studentClass = WILDCARD_CHARACTER;
    private String gender = WILDCARD_CHARACTER;
    private String caste = WILDCARD_CHARACTER;
    private String communityLocation = WILDCARD_CHARACTER;
    private String ageFrom = WILDCARD_CHARACTER;
    private String ageTo = WILDCARD_CHARACTER;
    private List<Talent> talents = new ArrayList<Talent>();
    private String religion = WILDCARD_CHARACTER;
    private String status = "Existing Student";
    private String caregiversOccupation = WILDCARD_CHARACTER;
    private String familyStatus = WILDCARD_CHARACTER;
    private String sponsored = WILDCARD_CHARACTER;
    private String sponsorName = "";


    public StudentSearchParameter(String name, String studentClass, String gender, String caste, String communityLocation,
                                  String ageFrom, String ageTo, List<Talent> talents, String religion, String status,
                                  String caregiversOccupation, String familyStatus, String sponsored, String sponsorName) {
        this.name = name;
        this.studentClass = studentClass;
        this.gender = gender;
        this.caste = caste;
        this.communityLocation = communityLocation;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.talents = talents;
        this.religion = religion;
        this.status = status;
        this.caregiversOccupation = caregiversOccupation;
        this.familyStatus = familyStatus;
        this.sponsored = sponsored;
        this.sponsorName = sponsorName;
    }

    @RequiredByFramework
    public StudentSearchParameter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Talent> getTalents() {
        return talents;
    }

    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCaregiversOccupation() {
        return caregiversOccupation;
    }

    public void setCaregiversOccupation(String caregiversOccupation) {
        this.caregiversOccupation = caregiversOccupation;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getSponsored() {
        return sponsored;
    }

    public void setSponsored(String sponsored) {
        this.sponsored = sponsored;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
}
