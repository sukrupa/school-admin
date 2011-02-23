package org.sukrupa.student;

import java.util.ArrayList;
import java.util.List;

public class ReferenceData {

    private List<String> STUDENT_CLASSES;
    private List<String> GENDERS;
    private List<String> CASTES;
    private List<String> SUBCASTES;
    private List<String> COMMUNITY_LOCATIONS;
    private List<String> RELIGIONS;
    private List<String> TALENTS;
    private int AGES_FROM;
    private int AGES_TO;

    public ReferenceData(List<String> student_classes, List<String> genders, List<String> castes, List<String> subcastes, List<String> community_locations, List<String> religions, List<String> talents, int ages_from, int ages_to) {
        STUDENT_CLASSES = student_classes;
        GENDERS = genders;
        CASTES = castes;
        SUBCASTES = subcastes;
        COMMUNITY_LOCATIONS = community_locations;
        RELIGIONS = religions;
        TALENTS = talents;
        AGES_FROM = ages_from;
        AGES_TO = ages_to;
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
}