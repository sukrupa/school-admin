@Login
Feature: Add student record

@OnListOfStudentsPage
     Scenario: Go to Register a Student page
     When I "Add New Student" in the sidebar
     Then the Register a student page is displayed

     Scenario: Add new student details
     Given I am on the Add New Student page
     When I enter "SK2010082011" as "studentId"
     And I enter "Yael" as "name"
     And I enter "06-03-1982" as "dateOfBirth"
     And I select "Female" as "gender"
     And I "Register" the form
     Then the Edit Student Record: Yael page is displayed

     Scenario: Return from register a student page without adding a new student
     Given I am on the Add New Student page
     When I click "Cancel" button
     Then the List of Students page is displayed

     Scenario: Display error messages when fields are incomplete
     Given I am on the Add New Student page
     When I "Register" the form
     Then the message "Missing Student ID. Please re-enter." should be displayed
     Then the message "Missing Student Name. Please re-enter." should be displayed
     Then the message "Please enter a valid date format." should be displayed
     Then the message "Please select a gender." should be displayed

   Scenario: Display error message when New student with existing ID is entered for registration
    Given I am on the Add New Student page
    When I enter "SK2010082022" as "studentId"
     And I enter "Yael" as "name"
     And I enter "06-03-1982" as "dateOfBirth"
     And I select "Female" as "gender"
     And I "Register" the form
     Then the Edit Student Record: Yael page is displayed

     When I "Add New Student" in the sidebar
     And I enter "SK2010082022" as "studentId"
     And I enter "Jack" as "name"
     And I enter "12-12-1290" as "dateOfBirth"
     And I select "Male" as "gender"
     And I "Register" the form
     Then the message "Student with the same ID already exists." should be displayed


