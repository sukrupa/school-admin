    @Login
    Feature: Edit student profile

    Scenario: Add/Edit sponsor, and verify other student details are unchanged
    Given I am on the View Students page
    When I choose "Bhavani" from student list
    And I "Edit" the form
    And I enter Peter Murray as Sponsor
    And I click "Save" button
    Then Student record updated successfully should be displayed

    And the Student Record: Bhavani page is displayed
    And "Peter Murray" is displayed under Sponsor

    When I "View Students" in the sidebar
    And I choose "Bhavani" from student list
    Then "A+" is displayed under Academic Performance
    And "Excellent" is displayed under Disciplinary
    And "Existing Student" is displayed under Student Status
    And "Peter Murray" is displayed under Sponsor

    Scenario:Adding a note to student profile
     Given I am on the View Students page
     When I choose "Bhavani" from student list
     And I "Edit" the form
     And I enter "This is a note" as the note
     And I "Add a Note" to the form
     Then Note Added Successfully should be displayed
     And "This is a note" should be displayed in the list of notes

     Scenario: Edit student's status successfully
     Given I am on the View Students page
     When I choose "Bhavani" from student list
     And I "Edit" the form
     And I select "Existing Student" as the Student Status
     And I click "Save" button
     Then Student record updated successfully should be displayed
     And the Student Record: Bhavani page is displayed
     And "Existing Student" is displayed under Student Status

     Scenario:Verify edit of family status success
     Given I am on the View Students page
     When I choose "Bhavani" from student list
     And I "Edit" the form
     And I select "Single" as the Family Status
     And I click "Save" button
     Then Student record updated successfully should be displayed
     And the Student Record: Bhavani page is displayed
     And "Single" is displayed under Family Status