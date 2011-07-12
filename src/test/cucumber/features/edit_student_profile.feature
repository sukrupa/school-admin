    @Login
    Feature: Edit student profile

    Scenario: Add/Edit sponsor, and verify other student details are unchanged
    When I edit student record of Bhavani
    And I enter Peter Murray as Sponsor
    And I save the changes
    Then the message "Student record updated successfully" is displayed
    And the "Student Record: Bhavani" page is displayed
    And Peter Murray is displayed under Sponsor

    When I navigate to the View Students page
    And I select the student record of Bhavani
    Then A+ is displayed under Academic Performance
    And Excellent is displayed under Disciplinary
    And Existing Student is displayed under Student Status
    And Peter Murray is displayed under Sponsor
    
    Scenario:Adding a note to student profile
    When I edit student record of Bhavani
    And I enter "This is a note" as the note
    And I Add a Note to the student profile
    Then the message "Note Added Successfully" is displayed
    And "This is a note" should be displayed in the list of notes

    Scenario: Edit student's status successfully
    When I edit student record of Bhavani
    And I select Existing Student for Student Status
    And I save the changes
    Then the message "Student record updated successfully" is displayed
    And the "Student Record: Bhavani" page is displayed
    And Existing Student is displayed under Student Status

    Scenario:Verify edit of family status success
    When I edit student record of Bhavani
    And I select Single for Family Status
    And I save the changes
    Then the message "Student record updated successfully" is displayed
    And the "Student Record: Bhavani" page is displayed
    And Single is displayed under Family Status