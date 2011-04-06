package org.sukrupa.student;


public enum StudentStatus {
    NOT_SET(0, ""), DROPOUT(1, "Dropout"), EXISTING_STUDENT(2, "Existing Student"), ALUMNI(3, "Alumni");

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
              returnValue = StudentStatus.EXISTING_STUDENT;
          }
          else if(valueIn.equals("Dropout"))
          {
              returnValue = StudentStatus.DROPOUT;
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
