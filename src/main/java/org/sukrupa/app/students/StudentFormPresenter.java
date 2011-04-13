package org.sukrupa.app.students;

import org.sukrupa.student.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

public class StudentFormPresenter {

    private final StudentFormReferenceData studentFormReferenceData = new StudentFormReferenceData();
    private final Student student;

    private TalentRepository talentRepository;

    public StudentFormPresenter(Student student, TalentRepository talentRepository) {
        this.student = student;
        this.talentRepository = talentRepository;
    }

    public List<CheckBoxElement> getTalentsCheckBoxList() {
        List<Talent> talents = talentRepository.listAllTalents();
        List<String> talentDescriptions = new ArrayList<String>();

        for (Talent talent : talents) {
            talentDescriptions.add(talent.getDescription());
        }

        return createCheckBoxList(talentDescriptions, student.talentDescriptions());
    }

    public List<DropDownElement> getSubCastesDropDownList() {
        return createDropDownList(studentFormReferenceData.getSubcastes(), student.getSubCaste());
    }

    public List<DropDownElement> getReligionsDropDownList() {
        return createDropDownList(studentFormReferenceData.getReligions(), student.getReligion());
    }

    public List<DropDownElement> getCommunityLocationsDropDownList() {
        return createDropDownList(studentFormReferenceData.getCommunityLocations(), student.getCommunityLocation());
    }

    public List<DropDownElement> getFamilyStatusesDropDownList() {
        return createDropDownList(studentFormReferenceData.getFamilyStatuses(), student.getFamilyStatus());
    }


    public List<DropDownElement> getCastesDropDownList() {
        return createDropDownList(studentFormReferenceData.getCastes(), student.getCaste());
    }

    public List<DropDownElement> getGendersDropDownList() {
        return createDropDownList(studentFormReferenceData.getGenders(), student.getGender());
    }

    public List<DropDownElement> getClassesDropDownList() {
        return createDropDownList(studentFormReferenceData.getStudentClasses(), student.getStudentClass());
    }

    public List<DropDownElement> getStatusesDropDownList() {
        return createDropDownList(studentFormReferenceData.getStatuses(), student.getStatus().getName());
    }

    public List<DropDownElement> getFatherOccupationsDropDownList() {
        if (student.getFather() == null) {
            return createDropdownList(studentFormReferenceData.getOccupations());
        } else {
            return createDropDownList(studentFormReferenceData.getOccupations(), student.getFather().getOccupation());
        }
    }

    public List<DropDownElement> getMotherOccupationsDropDownList() {
        if (student.getMother() == null) {
            return createDropdownList(studentFormReferenceData.getOccupations());
        } else {
            return createDropDownList(studentFormReferenceData.getOccupations(), student.getMother().getOccupation());
        }
    }

    public List<DropDownElement> getGuardianOccupationsDropDownList() {
        if (student.getGuardian() == null) {
            return createDropdownList(studentFormReferenceData.getOccupations());
        } else {
            return createDropDownList(studentFormReferenceData.getOccupations(), student.getGuardian().getOccupation());
        }
    }

    public List<DropDownElement> getFatherMaritalStatusesDropDownList() {
        if (student.getFather() == null) {
            return createDropdownList(studentFormReferenceData.getMaritalStatuses());
        } else {
            return createDropDownList(studentFormReferenceData.getMaritalStatuses(), student.getFather().getMaritalStatus());
        }
    }

    public List<DropDownElement> getMotherMaritalStatusesDropDownList() {
        if (student.getMother() == null) {
            return createDropdownList(studentFormReferenceData.getMaritalStatuses());
        } else {
            return createDropDownList(studentFormReferenceData.getMaritalStatuses(), student.getMother().getMaritalStatus());
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