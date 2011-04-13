package org.sukrupa.student;

import java.util.ArrayList;
import java.util.List;

public class StudentFormReferenceData {

    private final List<String> STUDENT_CLASSES;
    private final List<String> GENDERS;
    private final List<String> CASTES;
    private final List<String> SUBCASTES;
    private final List<String> COMMUNITY_LOCATIONS;
    private final List<String> RELIGIONS;
    private final List<String> TALENTS;
    private final List<String> STATUSES;
    private final boolean SPONSORED;
    private final List<String> OCCUPATIONS;
    private final List<String> MARITAL_STATUSES;
    private final int AGES_FROM;
    private final int AGES_TO;
    private final List<String> FAMILY_STATUSES;


    public StudentFormReferenceData(List<String> student_classes, List<String> genders, List<String> castes, List<String> subcastes,
                                    List<String> community_locations, List<String> religions, List<String> talents, int ages_from, int ages_to,
                                    List<String> statusesIn, boolean sponsored, List<String> occupations, List<String> marital_statuses, List<String> family_statuses) {
        STUDENT_CLASSES = student_classes;
        GENDERS = genders;
        CASTES = castes;
        SUBCASTES = subcastes;
        COMMUNITY_LOCATIONS = community_locations;
        RELIGIONS = religions;
        TALENTS = talents;
        AGES_FROM = ages_from;
        AGES_TO = ages_to;
        STATUSES = statusesIn;
        SPONSORED = sponsored;
        OCCUPATIONS = occupations;
        MARITAL_STATUSES = marital_statuses;
        FAMILY_STATUSES = family_statuses;
    }

    public List<String> getStudentClasses() {
        return STUDENT_CLASSES;
    }

    public List<String> getGenders() {
        return GENDERS;
    }

    public List<String> getCastes() {
        return CASTES;
    }

    public List<String> getSubcastes() {
        return SUBCASTES;
    }

    public List<String> getCommunityLocations() {
        return COMMUNITY_LOCATIONS;
    }

    public List<String> getReligions() {
        return RELIGIONS;
    }

    public List<String> getTalents() {
        return TALENTS;
    }

    public List<String> getAges() {
        List<String> ages = new ArrayList<String>();
        for (int age = AGES_FROM; age <= AGES_TO; age++) {
            ages.add(age + "");
        }
        return ages;
    }

    public int getAgesTo() {
        return AGES_TO;
    }

    public int getAgesFrom() {
        return AGES_FROM;
    }

    public List<String> getStatuses() {
        return STATUSES;
    }

    public boolean getSponsored() {
        return SPONSORED;
    }

    public List<String> getOccupations() {
        return OCCUPATIONS;
    }

    public List<String> getMaritalStatuses() {
        return MARITAL_STATUSES;
    }

    public List<String> getFamilyStatuses() {
        return FAMILY_STATUSES;
    }
}