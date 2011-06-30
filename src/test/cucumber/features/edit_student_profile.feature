@Login
Feature: Edit student profile

@OnListOfStudentsPage
     Scenario: Add/Edit sponsor, and verify other student details are unchanged
     When I choose "Bhavani" from student list
     And I "Edit" the form
    And I enter "Peter Murray" as the Sponsor
    And I click "Save" button
    Then the "Student Record: Bhavani" page is displayed
    And "Peter Murray" is displayed under Sponsor

       When I "View Students" in the sidebar
       And I choose "Bhavani" from student list
       Then "A+" is displayed under Academic Performance
       And "Excellent" is displayed under Disciplinary
       And "Existing Student" is displayed under Student Status
       And "Peter Murray" is displayed under Sponsor

