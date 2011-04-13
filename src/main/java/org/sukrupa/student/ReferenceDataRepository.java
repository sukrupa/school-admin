package org.sukrupa.student;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReferenceDataRepository {

    private final List<String> STUDENT_CLASSES = Arrays.asList("Preschool", "LKG", "UKG", "1 Std", "2 Std", "3 Std", "4 Std", "5 Std", "6 Std", "7 Std", "8 Std", "9 Std", "10 Std");
    private final List<String> GENDERS = Arrays.asList("", "Male", "Female");
    private final List<String> CASTES = Arrays.asList("", "Achari", "Agnikula", "Arya Vashya", "Baljigru", "Bhramin", "Bohvi", "Chettyar",
            "Gowdas", "Gownder", "MBC", "Modahaliyar", "Nadar", "Naidu", "Nayak", "Others", "Rajput", "Rathore", "Reddy's", "SC", "Shalai Keta",
            "Shetty", "ST", "Tigalaru", "Vanniyar", "Vishwa Karma");
    private final List<String> SUBCASTES = Arrays.asList("", "Adi Drawida", "Adi Janaga", "Adi Karnataka", "Bale -Balijigru", "Bale Banjaguru", "BC",
            "Bhajanthri", "Ganiga Shetty", "II 'A'", "Kamala Achari", "Kshathriya", "Kumbar Shetty", "Singh", "Tiwari", "Vailu Shetty", "Vakkaliga",
            "Val Nayak", "Vaniga Gownder", "Vannikula");
    private final List<String> COMMUNITY_LOCATIONS = Arrays.asList("", "Bhuvaneshwari Slum", "Chamundi Nagar",
            "Cholanayakanhalli", "Ganganagar", "Guddadahalli", "Hebbal", "Kanakanagar", "Kunthigrama", "Nagenahalli",
            "Rehmath Nagar", "Residential", "Subramanyanagar");

    private final List<String> FAMILY_STATUSES = Arrays.asList("", "General", "Single", "Destitute", "Orphan");

    private final List<String> OCCUPATIONS = Arrays.asList(" ", "Assitant Captain", "Auto Driver", "Baker", "Bar Bender", "Barman",
            "Bus Driver", "Cab Driver", "Cable TV Worker", "Candle Maker", "Carpenter", "Construction Worker", "Cook", "Coolie",
            "Domestic Worker", "Driver", "Driving School Faculty", "Electrician", "Embroidery Worker", "Factory Worker", "Farmer", "Flower Vendor",
            "Gardener", "Groundnut Vendor", "Head Constable", "House Keeper", "Iron Shop Worker", "Maid", "Nursing Assistant","Office Assistant",
            "Office Boy", "Office Man", "Painter","Recycle Shop Owner", "Saree Vendor", "Security Guard", "Server in Restaurant","Store Keeper",
            "Tailor", "Teacher", "Truck Driver", "TV Operator","Ward Boy","Welder");
    private final List<String> RELIGIONS = Arrays.asList("", "Christian", "Hindu", "Muslim", "Sikh");

    private final List<String> TALENTS = Arrays.asList("Acting", "Arts & Crafts", "Creative Writing", "Dancing", "Mimicry",
            "Musical Instrument", "Pick & Speak", "Public Speaking", "Reading", "Singing", "Sports", "Story Telling");
    private final int AGES_TO = 20;
    private final int AGES_FROM = 2;
    private final List<String> STATUSES = Arrays.asList("Existing Student", "Dropout", "Alumni");

    private final boolean SPONSORED = false;
    private final List<String> MARITAL_STATUSES = Arrays.asList(" ","Married", "Single", "Divorced", "Widowed", "Deceased");

    private StudentFormReferenceData studentFormReferenceData = new StudentFormReferenceData(STUDENT_CLASSES, GENDERS, CASTES, SUBCASTES, COMMUNITY_LOCATIONS, RELIGIONS, TALENTS, AGES_FROM, AGES_TO, STATUSES, SPONSORED, OCCUPATIONS, MARITAL_STATUSES, FAMILY_STATUSES);

    public StudentFormReferenceData getStudentFormReferenceData() {
        return studentFormReferenceData;
    }
}
