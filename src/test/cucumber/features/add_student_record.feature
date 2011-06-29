@Login
Feature: Add student record

@OnListOfStudentsPage
      Scenario: Go to register a student page
      When I click on "Add New Student"
      Then I should see the "Register a student" Page

@OnRegisterAStudentPage
      Scenario: Add new student details
      When I fill in the studentId with "SK2010082011"
      And I fill in the name as "Yael"
      And I fill in the dateOfBirth as "06-03-1982"
      And I select the gender as "Female"
      And I click "Register" submit button
      Then I should see the "Edit Student Record: Yael" page