package org.sukrupa.student;


import java.util.ArrayList;
import java.util.List;

public enum StudentStatus {
    EXISTING_STUDENT(0, "Existing Student"), DROPOUT(1, "Dropout"), ALUMNI(2, "Alumni");

    private String name;
    private int id;

    StudentStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StudentStatus fromString(String valueIn) {
        for (StudentStatus status : StudentStatus.values()) {
            if (valueIn.equals(status.toString())){
                return status;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static List<String> getNamesList() {
        List<String> result = new ArrayList<String>();
        for (StudentStatus studentStatus : StudentStatus.values()) {
            result.add(studentStatus.toString());
        }
        return result;
    }
}
