package org.sukrupa.student;

public enum StudentClass {
    DAY_CARE("Day Care", "NURSERY"),
    NURSERY("Nursery", "LKG"),
    LKG("LKG", "UKG"),
    UKG("UKG", "ONE_STD"),
    ONE_STD("1 Std", "TWO_STD"),
    TWO_STD("2 Std", "THREE_STD"),
    THREE_STD("3 Std", "FOUR_STD"),
    FOUR_STD("4 Std", "FIVE_STD"),
    FIVE_STD("5 Std", "SIX_STD"),
    SIX_STD("6 Std", "SEVEN_STD"),
    SEVEN_STD("7 Std", "EIGHT_STD"),
    EIGHT_STD("8 Std", "NINE_STD"),
    NINE_STD("9 Std", "TEN_STD"),
    TEN_STD("10 Std", "TEN_STD");

    private String nextClass;
    private String displayName;

    StudentClass(String displayName, String nextClass) {
        this.nextClass = nextClass;
        this.displayName = displayName;
    }


    public StudentClass next() {
        return StudentClass.valueOf(nextClass);
    }

    public StudentClass previous(){
        if(displayName=="Day Care") return (StudentClass.DAY_CARE);
        if(displayName=="Nursery") return (StudentClass.DAY_CARE);
        if(displayName=="LKG") return (StudentClass.NURSERY);
        if(displayName=="UKG") return (StudentClass.LKG);
        if(displayName=="1 Std") return (StudentClass.UKG);
        if(displayName=="2 Std") return (StudentClass.ONE_STD);
        if(displayName=="3 Std") return (StudentClass.TWO_STD);
        if(displayName=="4 Std") return (StudentClass.THREE_STD);
        if(displayName=="5 Std") return (StudentClass.FOUR_STD);
        if(displayName=="6 Std") return (StudentClass.FIVE_STD);
        if(displayName=="7 Std") return (StudentClass.SIX_STD);
        if(displayName=="8 Std") return (StudentClass.SEVEN_STD);
        if(displayName=="9 Std") return (StudentClass.EIGHT_STD);
        if(displayName=="10 Std") return (StudentClass.NINE_STD);
        return StudentClass.NINE_STD;

    }

    public String displayName() {
        return displayName;
    }

    public static StudentClass fromDisplayName(String displayName) {

        StudentClass displayNameClass = null;
        for(StudentClass studentClass : StudentClass.values()) {
            if(studentClass.displayName.equals(displayName)) {
                displayNameClass = studentClass;
            }
        }
        return displayNameClass;
    }

}
