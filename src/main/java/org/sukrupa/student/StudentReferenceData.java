package org.sukrupa.student;

import org.hibernate.SessionFactory;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class StudentReferenceData {

    private static final List<String> STUDENT_CLASSES = asList("Daycare", "Preschool", "LKG", "UKG", "1 Std", "2 Std", "3 Std", "4 Std", "5 Std", "6 Std", "7 Std", "8 Std", "9 Std", "10 Std");
    private static final String EMPTY_OPTION = "";
    private static final List<String> GENDERS = asList(EMPTY_OPTION, "Male", "Female");
    private static final List<String> CASTES = asList(EMPTY_OPTION, "Achari", "Agnikula", "Arya Vashya", "Baljigru", "Bhramin", "Bohvi", "Chettyar",
            "Gowdas", "Gownder", "MBC", "Modahaliyar", "Nadar", "Naidu", "Nayak", "Others", "Rajput", "Rathore", "Reddy's", "SC", "Shalai Keta",
            "Shetty", "ST", "Tigalaru", "Vanniyar", "Vishwa Karma");
    private static final List<String> SUBCASTES = asList(EMPTY_OPTION, "Adi Drawida", "Adi Janaga", "Adi Karnataka", "Bale -Balijigru", "Bale Banjaguru", "BC",
            "Bhajanthri", "Ganiga Shetty", "II 'A'", "Kamala Achari", "Kshathriya", "Kumbar Shetty", "Singh", "Tiwari", "Vailu Shetty", "Vakkaliga",
            "Val Nayak", "Vaniga Gownder", "Vannikula");
    private static final List<String> COMMUNITY_LOCATIONS = asList(EMPTY_OPTION, "Bhuvaneshwari Slum", "Chamundi Nagar",
            "Cholanayakanhalli", "Ganganagar", "Guddadahalli", "Hebbal", "Kanakanagar", "Kunthigrama", "Nagenahalli",
            "Rehmath Nagar", "Residential", "Subramanyanagar");
//    private static final List<String> OCCUPATIONS = asList(" ", "Assitant Captain", "Auto Driver", "Baker", "Bar Bender", "Barman",
//            "Bus Driver", "Cab Driver", "Cable TV Worker", "Candle Maker", "Carpenter", "Construction Worker", "Cook", "Coolie",
//            "Domestic Worker", "Driver", "Driving School Faculty", "Electrician", "Embroidery Worker", "Factory Worker", "Farmer", "Flower Vendor",
//            "Gardener", "Groundnut Vendor", "Head Constable", "House Keeper", "Iron Shop Worker", "Maid", "Nursing Assistant", "Office Assistant",
//            "Office Boy", "Office Man", "Painter", "Recycle Shop Owner", "Saree Vendor", "Security Guard", "Server in Restaurant", "Store Keeper",
//            "Tailor", "Teacher", "Truck Driver", "TV Operator", "Ward Boy", "Welder");


    private static final List<String> OCCUPATIONS = asList("Assistant Captain", "Auto Driver", "Bakery Worker", "Bar Bainding",
            "Bar Shop", "Cable Work", "Candle Work", "Car Driver", "Carpenter", "Centring", "Centring Contract",
            "Construction Worker", "Contract Labourer", "Coolie", "Courier", "Daily Wages Labour", "Domestic Worker",
            "Driver", "Driving School", "Electrician", "Embroidery Worker", "Flower Seller", "Former", "Gardener",
            "Groundnut Sailer", "Hawker", "Head Canstable", "Hotel Server", "House Keeper", "House wife", "Iron Shop",
            "Kouli", "Labour", "Lorry Driver", "Mason Work", "Meason", "No job", "Office Assistant", "Office Boy",
            "Office Man", "Painter", "Paper Mart Business", "Sales Executive", "Sales Man", "Sarees Seller",
            "Security Guard", "Service", "Stone Designer", "Store work", "TV Operator", "Tailes Work", "Tailor",
            "Tea/Coffee Business", "Tiles Worker", "Truck Driver", "Vender", "Ward Boy", "Watch Man", "Welder",
            "Work shop", "Worker", "Working in Sukrupa");

    private static final List<String> RELIGIONS = asList(EMPTY_OPTION, "Christian", "Hindu", "Muslim", "Sikh");
    private static final List<String> TALENTS_DESCRIPTIONS = asList("Acting", "Art", "Crafts", "Creative Writing", "Dancing", "Mimicry",
            "Musical Instrument", "Pick & Speak", "Public Speaking", "Reading", "Singing", "Sports", "Story Telling");
    private static final int AGES_TO = 20;
    private static final int AGES_FROM = 2;
    private static final List<String> SPONSORED = asList("Yes","No");
    private static final List<String> MARITAL_STATUSES = asList(" ", "Married", "Single", "Divorced", "Widowed", "Deceased");
    private TalentRepository talentRepository;


    public StudentReferenceData() {

    }

    public StudentReferenceData(TalentRepository talentRepository) {
        this.talentRepository = talentRepository;
    }

    public List<String> getStudentClasses() {
        return STUDENT_CLASSES;
    }

    public List<String> getGenders() {
        return GENDERS;
    }

    public List<String> getCastes() {
        return CASTES;
    }

    public List<String> getSubcastes() {
        return SUBCASTES;
    }

    public List<String> getCommunityLocations() {
        return COMMUNITY_LOCATIONS;
    }

    public List<String> getReligions() {
        return RELIGIONS;
    }


    public List<String> getTalentDescriptions()
    {
        List<Talent> talents = getTalentsFromRepository();
        if(talents.isEmpty())
        {
            talents = Collections.emptyList();
        }


        return (talentRepository.returnTalentDescriptionsInList(talents));

    }


    public List<String> getAges() {
        List<String> ages = new ArrayList<String>();
        for (int age = AGES_FROM; age <= AGES_TO; age++) {
            ages.add(age + EMPTY_OPTION);
        }
        return ages;
    }

    public int getAgesTo() {
        return AGES_TO;
    }

    public int getAgesFrom() {
        return AGES_FROM;
    }

    public List<String> getStatuses() {
        return StudentStatus.getNamesList();
    }

    public List<String> getSponsored() {
        return SPONSORED;
    }

    public List<String> getOccupations() {
        return OCCUPATIONS;
    }

    public List<String> getMaritalStatuses() {
        return MARITAL_STATUSES;
    }

    public List<String> getFamilyStatuses() {
        List<String> statusList = StudentFamilyStatus.getNameList();
        statusList.add(0, EMPTY_OPTION);
        return statusList;
    }

    public List<Talent> getTalentsFromRepository() {
        return (talentRepository == null)
                ? Collections.<Talent>emptyList()
                : talentRepository.findAllTalents();
    }

}