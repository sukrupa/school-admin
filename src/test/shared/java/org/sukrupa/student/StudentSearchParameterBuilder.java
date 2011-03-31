package org.sukrupa.student;

import java.util.ArrayList;
import java.util.List;

public class StudentSearchParameterBuilder {

    private String name = StudentSearchParameter.WILDCARD_CHARACTER;
    private String studentClass = StudentSearchParameter.WILDCARD_CHARACTER;
    private String gender = StudentSearchParameter.WILDCARD_CHARACTER;
    private String caste = StudentSearchParameter.WILDCARD_CHARACTER;
    private String area = StudentSearchParameter.WILDCARD_CHARACTER;
    private String ageFrom = StudentSearchParameter.WILDCARD_CHARACTER;
    private String ageTo = StudentSearchParameter.WILDCARD_CHARACTER;
    private String talent = StudentSearchParameter.WILDCARD_CHARACTER;
    private String religion = StudentSearchParameter.WILDCARD_CHARACTER;
    private int page = 1;
    private List<Talent> talents = new ArrayList<Talent>();

    public StudentSearchParameterBuilder name(String name) {
        this.name = name;
        return this;
    }

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

    public StudentSearchParameter build() {
        return new StudentSearchParameter(name, studentClass, gender, caste, area, ageFrom, ageTo, talents, religion);
    }

    public StudentSearchParameterBuilder page(int page) {
        this.page = page;
        return this;
    }

    public StudentSearchParameterBuilder withTalents(List<Talent> talents) {
        this.talents = talents;
        return this;
    }
}
