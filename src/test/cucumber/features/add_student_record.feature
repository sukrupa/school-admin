@Login
Feature: Add student record

@OnListOfStudentsPage
     Scenario: Go to Register a Student page
     When I "Add New Student" in the sidebar
     Then the "Register a student" page is displayed

@OnRegisterAStudentPage
     Scenario: Add new student details
     When I enter the "studentId" as "SK2010082011"
     And I enter the "name" as "Yael"
     And I enter the "dateOfBirth" as "06-03-1982"
     And I enter the "gender" as "Female"
     And I click the "Register" button
     Then the "Edit Student Record: Yael" page is displayed

@OnRegisterAStudentPage
     Scenario: Return from register a student page without adding a new student
     When I click the "Cancel" button
     Then the "List of Students" page is displayed

