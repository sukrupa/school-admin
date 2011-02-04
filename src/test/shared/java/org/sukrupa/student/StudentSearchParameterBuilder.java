package org.sukrupa.student;

public class StudentSearchParameterBuilder {

	private String studentClass = "";
    private String gender = "";
    private String caste = "";
    private String area = "";
	private String ageFrom = "";
	private String ageTo = "";
	private String talent = "";
	private String religion = "";

	public StudentSearchParameterBuilder studentClass(String studentClass) {
		this.studentClass = studentClass;
		return this;
	}


	public StudentSearchParameterBuilder gender(String gender) {
		this.gender = gender;
		return this;
	}


	public StudentSearchParameterBuilder caste(String caste) {
		this.caste = caste;
		return this;
	}

	public StudentSearchParameterBuilder area(String area) {
		this.area = area;
		return this;
	}



	public StudentSearchParameterBuilder ageFrom(String ageFrom) {
		this.ageFrom = ageFrom;
		return this;
	}



	public StudentSearchParameterBuilder ageTo(String ageTo) {
		this.ageTo = ageTo;
		return this;
	}


	public StudentSearchParameterBuilder talent(String talent) {
		this.talent = talent;
		return this;
	}


	public StudentSearchParameterBuilder religion(String religion) {
		this.religion = religion;
		return this;
	}

	public StudentSearchParameter build(){
		return new StudentSearchParameter(studentClass, gender, caste, area, ageFrom, ageTo, talent, religion);
	}

}
