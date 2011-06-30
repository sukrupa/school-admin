@Login
Feature: Edit student profile

@OnListOfStudentsPage
     Scenario: Add/Edit sponsor, and verify other student details are unchanged
     When I choose "Bhavani" from student list
     And I "Edit" the form
    And I enter "Peter Murray" as the Sponsor
    And I click "Save" button
    Then  "Peter Murray" should be displayed in Sponsor