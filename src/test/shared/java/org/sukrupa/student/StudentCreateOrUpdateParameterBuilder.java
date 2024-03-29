package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.sukrupa.platform.date.Date;

import java.util.Set;

/*
* This class is meant to help us create StudentForm Instance for testing
*/
public class StudentCreateOrUpdateParameterBuilder {
    private String studentId;
    private String name;
    private String dateOfBirth = DateTimeFormat.forPattern("dd-MM-YYYY").print(new LocalDate());
    private String gender;
    private String studentClass;
    private String religion;
    private String caste;
    private String subCaste;
    private String area;
    private Caregiver father;
    private Caregiver mother;
    private Caregiver guardian;
    private Set<String> talents;
    private StudentStatus status = StudentStatus.EXISTING_STUDENT;
    private String disciplinary;
    private String performance;
    private String background;
    private String sponsored;
    private String sponsorEmail;
    private String sponsorStartDate = DateTimeFormat.forPattern("dd-MM-YYYY").print(new LocalDate());
    private String familyStatus;
    private CommonsMultipartFile imageToUpload;


    public StudentCreateOrUpdateParameterBuilder studentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder imageToUpload(CommonsMultipartFile imageToUpload) {
        this.imageToUpload = imageToUpload;
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

    public StudentCreateOrUpdateParameterBuilder father(Caregiver father) {
        this.father = father;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder mother(Caregiver mother) {
        this.mother = mother;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder guardian(Caregiver guardian) {
        this.guardian = guardian;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder talents(Set<String> talents) {
        this.talents = talents;
        return this;
    }

    public StudentForm build() {
        return new StudentForm(studentId, name, dateOfBirth, gender, studentClass, religion, caste, subCaste, area, father, mother, guardian,
                talents, status.toString(), disciplinary, performance, background, familyStatus, imageToUpload, sponsored, sponsorEmail, sponsorStartDate);
    }


    public StudentCreateOrUpdateParameterBuilder status(StudentStatus statusIn) {
        this.status = statusIn;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder sponsored(String sponsored) {
        this.sponsored = sponsored;
        return this;
    }
    public StudentCreateOrUpdateParameterBuilder sponsorEmail(String sponsorEmail) {
        this.sponsorEmail = sponsorEmail;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder sponsorStartDate(String sponsorStartDate) {
        this.sponsorStartDate = sponsorStartDate;
        return this;
    }

    public StudentCreateOrUpdateParameterBuilder familyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
        return this;
    }
}