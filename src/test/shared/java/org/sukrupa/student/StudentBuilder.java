package org.sukrupa.student;

public class StudentBuilder {
    private String name;
    private String religion;
    private String caste;
    private String subCaste;
    private String area;
	private String studentId;
	private String sex;
	private String studentClass;
	private String dateOfBirth;

	public StudentBuilder name(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder religion(String religion) {
        this.religion = religion;
        return this;
    }

    public StudentBuilder caste(String caste) {
        this.caste = caste;
        return this;
    }

    public StudentBuilder subCaste(String subCaste) {
        this.subCaste = subCaste;
        return this;
    }

    public StudentBuilder area(String area) {
        this.area = area;
        return this;
    }

    public StudentBuilder studentId(String studentId) {
	    this.studentId = studentId;
        return this;
    }

    public StudentBuilder sex(String sex) {
	    this.sex = sex;
        return this;
    }

    public StudentBuilder studentClass(String studentClass) {
	    this.studentClass = studentClass;
	    return this;
    }

    public StudentBuilder dateOfBirth(String dateOfBirth) {
	    this.dateOfBirth = dateOfBirth;
	    return this;
    }

    public Student build() {
	    return new Student(studentId, name, religion, caste, subCaste, area, sex, studentClass, dateOfBirth);
    }
}