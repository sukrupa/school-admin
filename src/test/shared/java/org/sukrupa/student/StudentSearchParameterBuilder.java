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
    private StudentStatus status = StudentStatus.EXISTING_STUDENT;
    private String caregiversOccupation = StudentSearchParameter.WILDCARD_CHARACTER;
    private String familyStatus = StudentSearchParameter.WILDCARD_CHARACTER;
    private String sponsored = StudentSearchParameter.WILDCARD_CHARACTER;
    private String sponsorName = "";
    private String sponsor_email="";

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

        return new StudentSearchParameter(name, studentClass,
                gender, caste, area, ageFrom, ageTo, talents, religion, status.toString(),
                caregiversOccupation, familyStatus, sponsored, sponsorName, sponsor_email);
    }

    public StudentSearchParameterBuilder page(int page) {
        this.page = page;
        return this;
    }

    public StudentSearchParameterBuilder withTalents(List<Talent> talents) {
        this.talents = talents;
        return this;
    }

    public StudentSearchParameterBuilder studentStatus(StudentStatus status) {
        this.status = status;
        return this;
    }

    public StudentSearchParameterBuilder caregiversOccupation(String caregiversOccupation) {
        this.caregiversOccupation = caregiversOccupation;
        return this;
    }

    public StudentSearchParameterBuilder studentFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
        return this;
    }

    public StudentSearchParameterBuilder sponsored(String sponsoredStatus) {
        this.sponsored = sponsoredStatus;
        return this;
    }

    public StudentSearchParameterBuilder sponsor(String sponsorName) {
        this.sponsorName = sponsorName;
        return this;
    }
}
