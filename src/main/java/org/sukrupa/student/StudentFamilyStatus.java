package org.sukrupa.student;

import java.util.ArrayList;
import java.util.List;

public enum StudentFamilyStatus {

    GENERAL(0, "General"), SINGLE(1, "Single"), DESTITUTE(2, "Destitute"), ORPHAN(3, "Orphan");
    private int id;
    private String name;

    StudentFamilyStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<String> getNameList() {
        List<String> nameList = new ArrayList<String>();
        for (StudentFamilyStatus status : StudentFamilyStatus.values()) {
            nameList.add(status.toString());
        }
        return nameList;
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
}
