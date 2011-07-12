package org.sukrupa.student;

import java.util.Comparator;

public class StudentNameComparator implements Comparator{
    
    public int compare(Object student1, Object student2){
        String name1 = ((Student)student1).getName();
        String name2 = ((Student)student2).getName();
        return name1.compareTo(name2);
    }
}
