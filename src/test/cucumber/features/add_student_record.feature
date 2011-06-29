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
     And I click "Register" button
     Then the "Edit Student Record: Yael" page is displayed

@OnRegisterAStudentPage
     Scenario: Return from register a student page without adding a new student
     When I click "Cancel" button
     Then the "List of Students" page is displayed

