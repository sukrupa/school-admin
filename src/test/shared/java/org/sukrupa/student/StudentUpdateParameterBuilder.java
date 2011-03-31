package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.sukrupa.platform.date.Date;

import java.util.Set;

public class StudentUpdateParameterBuilder {
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

    public StudentUpdateParameterBuilder studentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public StudentUpdateParameterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public StudentUpdateParameterBuilder dateOfBirth(Date date) {
        return dateOfBirth(date.toString());
    }

    public StudentUpdateParameterBuilder dateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public StudentUpdateParameterBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public StudentUpdateParameterBuilder studentClass(String studentClass) {
        this.studentClass = studentClass;
        return this;
    }

    public StudentUpdateParameterBuilder religion(String religion) {
        this.religion = religion;
        return this;
    }

    public StudentUpdateParameterBuilder caste(String caste) {
        this.caste = caste;
        return this;
    }

    public StudentUpdateParameterBuilder subCaste(String subCaste) {
        this.subCaste = subCaste;
        return this;
    }

    public StudentUpdateParameterBuilder area(String area) {
        this.area = area;
        return this;
    }

    public StudentUpdateParameterBuilder father(String father) {
        this.father = father;
        return this;
    }

    public StudentUpdateParameterBuilder mother(String mother) {
        this.mother = mother;
        return this;
    }

    public StudentUpdateParameterBuilder talents(Set<String> talents) {
        this.talents = talents;
        return this;
    }

    public StudentCreateOrUpdateParameter build() {
        return new StudentCreateOrUpdateParameter(studentId, name, dateOfBirth, gender, studentClass, religion, caste, subCaste, area, father, mother, talents);
    }


}