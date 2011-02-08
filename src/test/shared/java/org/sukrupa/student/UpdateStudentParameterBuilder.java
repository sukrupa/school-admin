package org.sukrupa.student;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

public class UpdateStudentParameterBuilder {
    private String studentId;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String studentClass;
    private String religion;
    private String caste;
    private String subCaste;
    private String area;
    private String father = "father";
    private String mother = "mother";
    private Set<String> talents;

    public UpdateStudentParameterBuilder studentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public UpdateStudentParameterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UpdateStudentParameterBuilder dateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UpdateStudentParameterBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public UpdateStudentParameterBuilder studentClass(String studentClass) {
        this.studentClass = studentClass;
        return this;
    }

    public UpdateStudentParameterBuilder religion(String religion) {
        this.religion = religion;
        return this;
    }

    public UpdateStudentParameterBuilder caste(String caste) {
        this.caste = caste;
        return this;
    }

    public UpdateStudentParameterBuilder subCaste(String subCaste) {
        this.subCaste = subCaste;
        return this;
    }

    public UpdateStudentParameterBuilder area(String area) {
        this.area = area;
        return this;
    }

    public UpdateStudentParameterBuilder father(String father) {
        this.father = father;
        return this;
    }

    public UpdateStudentParameterBuilder mother(String mother) {
        this.mother = mother;
        return this;
    }

    public UpdateStudentParameterBuilder talents(Set<String> talents) {
        this.talents = talents;
        return this;
    }

    public UpdateStudentParameter build() {
        return new UpdateStudentParameter(studentId, name, dateOfBirth, gender, studentClass, religion, caste, subCaste, area, father, mother, talents);
    }
}