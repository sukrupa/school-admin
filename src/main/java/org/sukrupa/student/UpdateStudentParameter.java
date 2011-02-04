package org.sukrupa.student;

import org.sukrupa.platform.DoNotRemove;

public class UpdateStudentParameter {
	private String studentId;
    private String name;
    private String dateOfBirth;
    private String gender;
	private String studentClass;
	private String religion;
	private String caste;
	private String subCaste;
	private String area;
	private String father;
	private String mother;
    private String talents;

    public UpdateStudentParameter(String studentId, String name, String dateOfBirth, String gender, String studentClass, String religion, String caste, String subCaste, String area, String father, String mother, String talents) {
        this.studentId = studentId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.studentClass = studentClass;
        this.religion = religion;
        this.caste = caste;
        this.subCaste = subCaste;
        this.area = area;
        this.father = father;
        this.mother = mother;
        this.talents = talents;
    }

    @DoNotRemove
	public UpdateStudentParameter() {
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

    public String getArea() {
        return area;
    }

    public String getFather() {
        return father;
    }

    public String getMother() {
        return mother;
    }

    public String getTalents() {
        return talents;
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

    public void setArea(String area) {
        this.area = area;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public void setTalents(String talents) {
        this.talents = talents;
    }
}
