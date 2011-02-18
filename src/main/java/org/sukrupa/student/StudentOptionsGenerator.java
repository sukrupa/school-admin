package org.sukrupa.student;

import java.util.List;

import static java.util.Arrays.asList;

public abstract class StudentOptionsGenerator {

    public static final List<String> STUDENT_CLASSES = asList("Preschool", "LKG", "UKG", "1 Std", "2 Std", "3 Std", "4 Std", "5 Std", "6 Std", "7 Std", "8 Std", "9 Std", "10 Std");
    public static final List<String> GENDERS = asList("Male", "Female");
    public static final List<String> CASTES = asList("", "Achari", "Agnikula", "Arya Vashya", "Baljigru", "Bhramin", "Bohvi", "Chettyar",
            "Gowdas", "Gownder", "MBC", "Modahaliyar", "Nadar", "Naidu", "Nayak", "Others", "Rajput", "Rathore", "Reddy's", "SC", "Shalai Keta",
            "Shetty", "ST", "Tigalaru", "Vanniyar", "Vishwa Karma");
    public static final List<String> SUBCASTES = asList("","Adi Drawida","Adi Janaga","Adi Karnataka","Bale -Balijigru","Bale Banjaguru","BC",
            "Bhajanthri","Ganiga Shetty","II 'A'","Kamala Achari","Kshathriya","Kumbar Shetty","Singh","Tiwari","Vailu Shetty","Vakkaliga",
            "Val Nayak","Vaniga Gownder","Vannikula");
    public static final List<String> COMMUNITY_LOCATIONS = asList("", "Bhuvaneshwari Slum", "Chamundi Nagar",
            "Cholanayakanhalli", "Ganganagar", "Guddadahalli", "Hebbal", "Kanakanagar", "Kunthigrama", "Nagenahalli",
            "Rehmath Nagar", "Residential", "Subramanyanagar");
    public static final List<String> RELIGIONS = asList("", "Christian", "Hindu", "Muslim", "Sikh");
    public static final List<String> TALENTS = asList("Acting", "Arts & Crafts", "Creative Writing", "Dancing", "Mimicry",
            "Musical Instrument", "Pick & Speak", "Public Speaking", "Reading", "Singing", "Sports", "Story Telling");
}
