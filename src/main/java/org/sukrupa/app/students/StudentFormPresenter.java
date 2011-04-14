package org.sukrupa.app.students;

import org.sukrupa.student.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class StudentFormPresenter {

    private final Student student;
    private final StudentReferenceData studentReferenceData;

    public StudentFormPresenter(Student student, StudentReferenceData studentReferenceData) {
        this.student = student;
        this.studentReferenceData = studentReferenceData;
    }

    public List<CheckBoxElement> getTalentsCheckBoxList() {
        return createCheckBoxList(studentReferenceData.getTalentDescriptions(), student.talentDescriptions());
    }

    public List<DropDownElement> getSubCastesDropDownList() {
        return createDropDownList(studentReferenceData.getSubcastes(), student.getSubCaste());
    }

    public List<DropDownElement> getReligionsDropDownList() {
        return createDropDownList(studentReferenceData.getReligions(), student.getReligion());
    }

    public List<DropDownElement> getCommunityLocationsDropDownList() {
        return createDropDownList(studentReferenceData.getCommunityLocations(), student.getCommunityLocation());
    }

    public List<DropDownElement> getFamilyStatusesDropDownList() {
        String status = student.getFamilyStatus() == null ? "" : student.getFamilyStatus().toString();
        return createDropDownList(studentReferenceData.getFamilyStatuses(), status);
    }


    public List<DropDownElement> getCastesDropDownList() {
        return createDropDownList(studentReferenceData.getCastes(), student.getCaste());
    }

    public List<DropDownElement> getGendersDropDownList() {
        return createDropDownList(studentReferenceData.getGenders(), student.getGender());
    }

    public List<DropDownElement> getClassesDropDownList() {
        return createDropDownList(studentReferenceData.getStudentClasses(), student.getStudentClass());
    }

    public List<DropDownElement> getStatusesDropDownList() {
        return createDropDownList(studentReferenceData.getStatuses(), student.getStatus().getName());
    }

    public List<DropDownElement> getFatherOccupationsDropDownList() {
        if (student.getFather() == null) {
            return createDropdownList(studentReferenceData.getOccupations());
        } else {
            return createDropDownList(studentReferenceData.getOccupations(), student.getFather().getOccupation());
        }
    }

    public List<DropDownElement> getMotherOccupationsDropDownList() {
        if (student.getMother() == null) {
            return createDropdownList(studentReferenceData.getOccupations());
        } else {
            return createDropDownList(studentReferenceData.getOccupations(), student.getMother().getOccupation());
        }
    }

    public List<DropDownElement> getGuardianOccupationsDropDownList() {
        if (student.getGuardian() == null) {
            return createDropdownList(studentReferenceData.getOccupations());
        } else {
            return createDropDownList(studentReferenceData.getOccupations(), student.getGuardian().getOccupation());
        }
    }

    public List<DropDownElement> getFatherMaritalStatusesDropDownList() {
        if (student.getFather() == null) {
            return createDropdownList(studentReferenceData.getMaritalStatuses());
        } else {
            return createDropDownList(studentReferenceData.getMaritalStatuses(), student.getFather().getMaritalStatus());
        }
    }

    public List<DropDownElement> getMotherMaritalStatusesDropDownList() {
        if (student.getMother() == null) {
            return createDropdownList(studentReferenceData.getMaritalStatuses());
        } else {
            return createDropDownList(studentReferenceData.getMaritalStatuses(), student.getMother().getMaritalStatus());
        }
    }

    private static List<DropDownElement> createDropdownList(List<String> values) {
        return createDropDownList(values, "");
    }

    private static List<DropDownElement> createDropDownList(List<String> values, String selectedValue) {
        List<DropDownElement> dropDownElements = new ArrayList<DropDownElement>();
        for (String value : values) {
            dropDownElements.add(new DropDownElement(value, value.equals(selectedValue)));
        }
        return dropDownElements;
    }

    private static List<CheckBoxElement> createCheckBoxList(List<String> values, List<String> selectedValues) {
        List<CheckBoxElement> checkBoxElements = new ArrayList<CheckBoxElement>();
        for (String value : values) {
            checkBoxElements.add(new CheckBoxElement(value, selectedValues.contains(value)));
        }
        return checkBoxElements;
    }

    public static class DropDownElement {
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

    public static class CheckBoxElement {
        private final String value;
        private final boolean checked;

        public CheckBoxElement(String value, boolean checked) {
            this.value = value;
            this.checked = checked;
        }

        public boolean isChecked() {
            return checked;
        }

        public String getValue() {
            return value;
        }

        public String toString() {
            return format("[checkBox value=%s, checked=%s]", value, checked);
        }
    }

}