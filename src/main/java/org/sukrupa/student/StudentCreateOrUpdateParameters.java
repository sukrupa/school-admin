package org.sukrupa.student;

import org.joda.time.format.DateTimeFormat;
import org.sukrupa.platform.DoNotRemove;

import java.util.Set;

public class StudentCreateOrUpdateParameters {
	private String studentId;
    private String name;
    private String dateOfBirth;
    private String gender;
	private String studentClass;
	private String religion;
	private String caste;
	private String subCaste;
	private String communityLocation;
	private String father;
	private String mother;
    private Set<String> talents;
    private String status;
    private String disciplinary;
    private String performance;

    public StudentCreateOrUpdateParameters(String studentId, String name, String dateOfBirth, String gender, String studentClass, String religion, String caste, String subCaste, String communityLocation, String father, String mother, Set<String> talents, String status, String performance, String disciplinary) {
        this.studentId = studentId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.studentClass = studentClass;
        this.religion = religion;
        this.caste = caste;
        this.subCaste = subCaste;
        this.communityLocation = communityLocation;
        this.father = father;
        this.mother = mother;
        this.talents = talents;
        this.status = status;
        this.disciplinary = disciplinary;
        this.performance = performance;
    }

    @DoNotRemove
	public StudentCreateOrUpdateParameters() {
	}

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfBirthForDisplay() {
        return getDateOfBirth();
    }



    public String getGender() {
        return gender;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getReligion() {
        return religion;
    }

    public String getCaste() {
        return caste;
    }

    public String getSubCaste() {
        return subCaste;
    }

    public String getCommunityLocation() {
        return communityLocation;
    }

    public String getFather() {
        return father;
    }

    public String getMother() {
        return mother;
    }

    public Set<String> getTalentDescriptions() {
        return talents;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public void setSubCaste(String subCaste) {
        this.subCaste = subCaste;
    }

    public void setCommunityLocation(String communityLocation) {
        this.communityLocation = communityLocation;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public void setTalents(Set<String> talents) {
        this.talents = talents;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public void setDisciplinary(String disciplinary) {
        this.disciplinary = disciplinary;
    }

    public String getPerformance() {
        return performance;
    }

    public String getDisciplinary() {
        return disciplinary;
    }
}
