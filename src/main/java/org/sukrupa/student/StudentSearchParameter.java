package org.sukrupa.student;

import org.sukrupa.platform.DoNotRemove;

public class StudentSearchParameter {

	private String studentClass;
    private String gender;
    private String caste;
    private String area;
	private String ageFrom;
	private String ageTo;
	private String talent;

	public StudentSearchParameter(String studentClass, String gender, String caste, String area, String ageFrom, String ageTo, String talent) {
		this.studentClass = studentClass;
		this.gender = gender;
		this.caste = caste;
		this.area = area;
		this.ageFrom = ageFrom;
		this.ageTo = ageTo;
		this.talent = talent;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getTalent() {
		return talent;
	}

	public void setTalent(String talent) {
		this.talent = talent;
	}

}