package org.sukrupa.app.students;

import org.sukrupa.student.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentEditFormHelper {
    final ReferenceData referenceData;
    private Student student;

    public StudentEditFormHelper(Student theStudent, ReferenceData referenceData) {
        this.student = theStudent;
        this.referenceData = referenceData;
    }

    List<DropDownElement> createDropDownList(List<String> values, String selectedValue) {
        List<DropDownElement> dropDownElements = new ArrayList<DropDownElement>();
        for (String value : values) {
            dropDownElements.add(new DropDownElement(value, value.equals(selectedValue)));
        }
        return dropDownElements;
    }

    List<CheckBoxElement> createCheckBoxList(List<String> values, List<String> selectedValues) {
        List<CheckBoxElement> checkBoxElements = new ArrayList<CheckBoxElement>();
        for (String value : values) {
            checkBoxElements.add(new CheckBoxElement(value, selectedValues.contains(value)));
        }
        return checkBoxElements;
    }

    public List<CheckBoxElement> getTalentsCheckBoxList() {
        return createCheckBoxList(referenceData.getTalents(), student.talentDescriptions());
    }

    public List<DropDownElement> getSubCastesDropDownList() {
        return createDropDownList(referenceData.getSubcastes(), student.getSubCaste());
    }

    public List<DropDownElement> getReligionsDropDownList() {
        return createDropDownList(referenceData.getReligions(), student.getReligion());
    }

    public List<DropDownElement> getCommunityLocationsDropDownList() {
        return createDropDownList(referenceData.getCommunityLocations(), student.getCommunityLocation());
    }

    public List<DropDownElement> getCastesDropDownList() {
        return createDropDownList(referenceData.getCastes(), student.getCaste());
    }

    public List<DropDownElement> getGendersDropDownList() {
        return createDropDownList(referenceData.getGenders(), student.getGender());
    }

    public List<DropDownElement> getClassesDropDownList() {
        return createDropDownList(referenceData.getStudentClasses(), student.getStudentClass());
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