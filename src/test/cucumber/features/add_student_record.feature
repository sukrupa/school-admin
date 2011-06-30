@Login
Feature: Add student record

@OnListOfStudentsPage
     Scenario: Go to Register a Student page
     When I "Add New Student" in the sidebar
     Then the "Register a student" page is displayed

@OnRegisterAStudentPage
     Scenario: Add new student details
     When I enter "SK2010082011" as "studentId"
     And I enter "Yael" as "name"
     And I enter "06-03-1982" as "dateOfBirth"
     And I select "Female" as "gender"
     And I click "Register" submit button
     Then the "Edit Student Record: Yael" page is displayed

@OnRegisterAStudentPage
     Scenario: Return from register a student page without adding a new student
     When I click "Cancel" button
     Then the "List of Students" page is displayed

@OnRegisterAStudentPage
     Scenario: Display error messages when fields are incomplete
     When I click "Register" submit button
     Then the error message "Missing Student ID. Please re-enter." is displayed
     Then the error message "Missing Student Name. Please re-enter." is displayed
     Then the error message "Please enter a valid date format." is displayed
     Then the error message "Please select a gender." is displayed

@OnRegisterAStudentPage
   Scenario: Display error message when New student with existing ID is entered for registration
    When I enter "SK2010082022" as "studentId"
     And I enter "Yael" as "name"
     And I enter "06-03-1982" as "dateOfBirth"
     And I select "Female" as "gender"
     And I click "Register" submit button
     Then the "Edit Student Record: Yael" page is displayed

     When I "Add New Student" in the sidebar
     And I enter "SK2010082022" as "studentId"
     And I enter "Jack" as "name"
     And I enter "12-12-1290" as "dateOfBirth"
     And I select "Male" as "gender"
     And I click "Register" submit button
     Then the error message "Student with the same ID already exists." is displayed


