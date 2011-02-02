package org.sukrupa.student;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class StudentBuilder {
    private static final String FEMALE = "female";
    private static final String MALE = "male";
    
    private String name;
    private String religion;
    private String caste;
    private String subCaste;
    private String area;
	private String studentId;
	private String sex;
	private String studentClass;
	private DateTime dateOfBirth;

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

    public StudentBuilder dateOfBirth(DateTime dateOfBirth) {
	    this.dateOfBirth = dateOfBirth;
	    return this;
    }

    public Student build() {
        return new Student(studentId, name, religion, caste, subCaste, area, sex, studentClass, dateOfBirth);
    }

    public StudentBuilder female() {
        sex = FEMALE;
        return this;
    }

    public StudentBuilder male() {
        sex = MALE;
        return this;
    }


}
