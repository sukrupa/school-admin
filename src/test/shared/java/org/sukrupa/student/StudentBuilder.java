package org.sukrupa.student;

public class StudentBuilder {
    private String name;
    private String religion;
    private String caste;
    private String subCaste;
    private String area;

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

    public Student build() {
        return new Student(name, religion, caste, subCaste, area);
    }
}
