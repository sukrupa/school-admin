package org.sukrupa.student;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReferenceDataRepository {

    private final List<String> STUDENT_CLASSES = Arrays.asList("Preschool", "LKG", "UKG", "1 Std", "2 Std", "3 Std", "4 Std", "5 Std", "6 Std", "7 Std", "8 Std", "9 Std", "10 Std");
    private final List<String> GENDERS = Arrays.asList("Male", "Female");
    private final List<String> CASTES = Arrays.asList("", "Achari", "Agnikula", "Arya Vashya", "Baljigru", "Bhramin", "Bohvi", "Chettyar",
            "Gowdas", "Gownder", "MBC", "Modahaliyar", "Nadar", "Naidu", "Nayak", "Others", "Rajput", "Rathore", "Reddy's", "SC", "Shalai Keta",
            "Shetty", "ST", "Tigalaru", "Vanniyar", "Vishwa Karma");
    private final List<String> SUBCASTES = Arrays.asList("", "Adi Drawida", "Adi Janaga", "Adi Karnataka", "Bale -Balijigru", "Bale Banjaguru", "BC",
            "Bhajanthri", "Ganiga Shetty", "II 'A'", "Kamala Achari", "Kshathriya", "Kumbar Shetty", "Singh", "Tiwari", "Vailu Shetty", "Vakkaliga",
            "Val Nayak", "Vaniga Gownder", "Vannikula");
    private final List<String> COMMUNITY_LOCATIONS = Arrays.asList("", "Bhuvaneshwari Slum", "Chamundi Nagar",
            "Cholanayakanhalli", "Ganganagar", "Guddadahalli", "Hebbal", "Kanakanagar", "Kunthigrama", "Nagenahalli",
            "Rehmath Nagar", "Residential", "Subramanyanagar");
    private final List<String> RELIGIONS = Arrays.asList("", "Christian", "Hindu", "Muslim", "Sikh");
    private final List<String> TALENTS = Arrays.asList("Acting", "Arts & Crafts", "Creative Writing", "Dancing", "Mimicry",
            "Musical Instrument", "Pick & Speak", "Public Speaking", "Reading", "Singing", "Sports", "Story Telling");
    private final int AGES_TO = 20;
    private final int AGES_FROM = 2;
    private final List<String> STATUSES = Arrays.asList("", "Existing Student", "Dropout", "Alumni");
    private ReferenceData referenceData = new ReferenceData(STUDENT_CLASSES, GENDERS, CASTES, SUBCASTES, COMMUNITY_LOCATIONS, RELIGIONS, TALENTS, AGES_FROM, AGES_TO, STATUSES);


    public ReferenceData getReferenceData() {
        return referenceData;
    }
}
