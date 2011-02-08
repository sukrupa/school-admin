package org.sukrupa.student;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

public class StudentBuilder {
    private static final String FEMALE = "female";
    private static final String MALE = "male";

    private String name;
    private String religion;
    private String caste;
    private String subCaste;
    private String area;
    private Set<Talent> talents = new HashSet<Talent>();
    private String studentId;
    private String gender;
    private String father;
    private String mother;
    private String studentClass;
    private LocalDate dateOfBirth = new LocalDate();
    private List<Note> notes = new ArrayList<Note>();


    public StudentBuilder notes(Note... notes) {
        this.notes = asList(notes);
        return this;
    }



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

    public StudentBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public StudentBuilder studentClass(String studentClass) {
        this.studentClass = studentClass;
        return this;
    }

    public StudentBuilder dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public StudentBuilder female() {
        gender = FEMALE;
        return this;
    }

    public StudentBuilder male() {
        gender = MALE;
        return this;
    }

    public StudentBuilder age(int age) {
        dateOfBirth = new LocalDate().minusYears(age);
        return this;
    }

    public StudentBuilder talents(Set<Talent> talents) {
        this.talents = talents;
        return this;
    }

    public StudentBuilder talents(Talent... talents) {
        talents(new HashSet<Talent>(asList(talents)));
        return this;
    }

    public StudentBuilder talents(String... talents) {
        Set<Talent> set = new HashSet<Talent>();
        for (String talent : talents) {
            set.add(new Talent(talent));
        }
        talents(set);
        return this;
    }

    public StudentBuilder father(String father) {
        this.father = father;
        return this;
    }

    public StudentBuilder mother(String mother) {
        this.mother = mother;
        return this;
    }

    public Student build() {
        return new Student(studentId, name, religion, caste, subCaste, area, gender, studentClass, talents, father, mother, dateOfBirth, notes);
    }
}
