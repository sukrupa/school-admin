package org.sukrupa.app.students;

import org.sukrupa.student.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentFormHelper {
    private final List<String> STUDENT_CLASSES = Arrays.asList("Preschool", "LKG", "UKG", "1 Std", "2 Std", "3 Std", "4 Std", "5 Std", "6 Std", "7 Std", "8 Std", "9 Std", "10 Std");
    private final List<String> GENDERS = Arrays.asList("Male", "Female");
    private final List<String> CASTES = Arrays.asList("", "Achari", "Agnikula", "Arya Vashya", "Baljigru", "Bhramin", "Bohvi", "Chettyar",
            "Gowdas", "Gownder", "MBC", "Modahaliyar", "Nadar", "Naidu", "Nayak", "Others", "Rajput", "Rathore", "Reddy's", "SC", "Shalai Keta",
            "Shetty", "ST", "Tigalaru", "Vanniyar", "Vishwa Karma");
    private final List<String> SUBCASTES = Arrays.asList("", "Adi Drawida", "Adi Janaga", "Adi Karnataka", "Bale -Balijigru", "Bale Banjaguru", "BC",
            "Bhajanthri", "Ganiga Shetty", "II 'A'", "Kamala Achari", "Kshathriya", "Kumbar Shetty", "Singh", "Tiwari", "Vailu Shetty", "Vakkaliga",
            "Val Nayak", "Vaniga Gownder", "Vannikula");
    private final List<String> COMMUNITY_LOCATIONS = Arrays.asList("", "Bhuvaneshwari Slum", "Chamundi Nagar",
            "Cholanayakanhalli", "Ganganagar", "Guddadahalli", "Hebbal", "Kanakanagar", "Kunthigrama", "Nagenahalli",
            "Rehmath Nagar", "Residential", "Subramanyanagar");
    private final List<String> RELIGIONS = Arrays.asList("", "Christian", "Hindu", "Muslim", "Sikh");
    private final List<String> TALENTS = Arrays.asList("Acting", "Arts & Crafts", "Creative Writing", "Dancing", "Mimicry",
            "Musical Instrument", "Pick & Speak", "Public Speaking", "Reading", "Singing", "Sports", "Story Telling");
    private final int AGES_TO = 20;
    private final int AGES_FROM = 2;
    private Student student;

    public StudentFormHelper() {
    }

    public StudentFormHelper(Student student) {
        this.student = student;
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

    public int getAgesTo() {
        return AGES_TO;
    }

    public int getAgesFrom() {
        return AGES_FROM;
    }

    private List<DropDownElement> createDropDownList(List<String> values, String selectedValue) {
        List<DropDownElement> dropDownElements = new ArrayList<DropDownElement>();
        for (String value : values) {
            dropDownElements.add(new DropDownElement(value, value.equals(selectedValue)));
        }
        return dropDownElements;
    }

    private List<CheckBoxElement> createCheckBoxList(List<String> values, List<String> selectedValues) {
        List<CheckBoxElement> checkBoxElements = new ArrayList<CheckBoxElement>();
        for (String value : values) {
            checkBoxElements.add(new CheckBoxElement(value, selectedValues.contains(value)));
        }
        return checkBoxElements;
    }

    public List<String> getAges() {
        List<String> ages = new ArrayList<String>();
        for (int age = getAgesFrom(); age <= getAgesTo(); age++) {
            ages.add(age + "");
        }
        return ages;
    }

    public List<CheckBoxElement> getTalentsCheckBoxList() {
        return createCheckBoxList(getTalents(), student.talentDescriptions());
    }

    public List<DropDownElement> getSubCastesDropDownList() {
        return createDropDownList(getSubcastes(), student.getSubCaste());
    }

    public List<DropDownElement> getReligionsDropDownList() {
        return createDropDownList(getReligions(), student.getReligion());
    }

    public List<DropDownElement> getCommunityLocationsDropDownList() {
        return createDropDownList(getCommunityLocations(), student.getCommunityLocation());
    }

    public List<DropDownElement> getCastesDropDownList() {
        return createDropDownList(getCastes(), student.getCaste());
    }

    public List<DropDownElement> getGendersDropDownList() {
        return createDropDownList(getGenders(), student.getGender());
    }

    public List<DropDownElement> getClassesDropDownList() {
        return createDropDownList(getStudentClasses(), student.getStudentClass());
    }

    static class DropDownElement {
        public boolean isSelected() {
            return selected;
        }

        public String getValue() {
            return value;
        }

        private final String value;
        private final boolean selected;

        public DropDownElement(String value, boolean selected) {
            this.value = value;
            this.selected = selected;
        }
    }

    static class CheckBoxElement {
        public boolean isChecked() {
            return checked;
        }

        public String getValue() {
            return value;
        }

        private final String value;
        private final boolean checked;

        public CheckBoxElement(String value, boolean checked) {
            this.value = value;
            this.checked = checked;
        }
    }
}