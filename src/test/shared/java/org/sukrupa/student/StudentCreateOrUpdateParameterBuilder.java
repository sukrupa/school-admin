package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.sukrupa.platform.date.Date;

import java.util.Set;

public class StudentCreateOrUpdateParameterBuilder {
    private String studentId;
    private String name;
    private String dateOfBirth = DateTimeFormat.forPattern("dd/MM/YYYY").print(new LocalDate());
    private String gender;
    private String studentClass;
    private String religion;
    private String caste;
    private String subCaste;
    private String area;
    private String father;
    private String mother;
    private Set<String> talents;
    private StudentStatus status = StudentStatus.NOT_SET;
    private String disciplinary;
    private String performance;
    private String background;

    public StudentCreateOrUpdateParameterBuilder studentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder dateOfBirth(Date date) {
        return dateOfBirth(date.toString());
    }

    public StudentCreateOrUpdateParameterBuilder dateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

     public StudentCreateOrUpdateParameterBuilder disciplinary(String disciplinary) {
        this.disciplinary = disciplinary;
        return this;
    }

     public StudentCreateOrUpdateParameterBuilder performance(String performance) {
        this.performance = performance;
        return this;
    }

     public StudentCreateOrUpdateParameterBuilder background(String background) {
        this.background = background;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder studentClass(String studentClass) {
        this.studentClass = studentClass;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder religion(String religion) {
        this.religion = religion;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder caste(String caste) {
        this.caste = caste;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder subCaste(String subCaste) {
        this.subCaste = subCaste;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder area(String area) {
        this.area = area;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder father(String father) {
        this.father = father;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder mother(String mother) {
        this.mother = mother;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder talents(Set<String> talents) {
        this.talents = talents;
        return this;
    }

    public StudentProfileForm build() {
        return new StudentProfileForm(studentId, name, dateOfBirth, gender, studentClass, religion, caste, subCaste, area, father, mother, talents, status.toString(), disciplinary, performance, background);
    }


    public StudentCreateOrUpdateParameterBuilder status(StudentStatus statusIn) {
        this.status = statusIn;
        return this;
    }
}