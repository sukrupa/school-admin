package org.sukrupa.student;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum StudentStatus {
    NOT_SET(0, ""), INACTIVE(1, "Dropout"), ACTIVE(2, "Existing Student"), ALUMNI(3, "Alumni");

    private String name;
    private int id;

    StudentStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StudentStatus fromString(String valueIn)
    {
         StudentStatus returnValue = null;
      for (StudentStatus value: StudentStatus.values() )
      {
          if(valueIn.equals("Existing Student"))
          {
              returnValue = StudentStatus.ACTIVE;
          }
          else if(valueIn.equals("Dropout"))
          {
              returnValue = StudentStatus.INACTIVE;
          }
          else if(valueIn.equals("Alumni"))
          {
              returnValue = StudentStatus.ALUMNI;
          }
      }
        return returnValue;
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
