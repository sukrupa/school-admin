Feature: Edit student profile

    @Login
    Scenario: Add/Edit sponsor, and verify other student details are unchanged
    When I edit student record of Bhavani
    And I enter Peter Murray as Sponsor
    And I enter Tim@gmail.com as SponsorEmail
    And I enter 13-07-2011 as SponsorStartDate
    And I save the changes
    Then the message "Student record updated successfully" is displayed
    And the "Student Record: Bhavani" page is displayed
    And Peter Murray is displayed under Sponsor
    And Tim@gmail.com is displayed under Sponsor Email
    And 13-07-2011 is displayed under Sponsor Start Date

    When I navigate to the View Students page
    And I select the student record of Bhavani
    Then A+ is displayed under Academic Performance
    And Excellent is displayed under Disciplinary
    And Existing Student is displayed under Student Status
    And Peter Murray is displayed under Sponsor
    
    Scenario:Adding a note to student profile
    Given I am on Sukrupa Page
    When I edit student record of Bhavani
    And I enter "This is a note" as the note
    And I Add a Note to the student profile
    Then the message "Note Added Successfully" is displayed
    And "This is a note" should be displayed in the list of notes

    Scenario: Edit student's status successfully
    Given I am on Sukrupa Page
    When I edit student record of Bhavani
    And I select Existing Student for Student Status
    And I save the changes
    Then the message "Student record updated successfully" is displayed
    And the "Student Record: Bhavani" page is displayed
    And Existing Student is displayed under Student Status

    Scenario:Verify edit of family status success
    Given I am on Sukrupa Page
    When I edit student record of Bhavani
    And I select Single for Family Status
    And I save the changes
    Then the message "Student record updated successfully" is displayed
    And the "Student Record: Bhavani" page is displayed
    And Single is displayed under Family Status

    Scenario: New acceptance criteria message
    Given I am on Sukrupa Page
    When I edit student record of Chandru
    Then the message "If adding a sponsor, enter all 3 following details" is displayed

    Scenario: Adding a sponsor without all required data
    Given I am on Sukrupa Page
    When I edit student record of Chandru
    And I enter Tim as Sponsor
    And I save the changes
    Then the message "Please enter all three sponsor fields - sponsor name, sponsor email and sponsor start date." is displayed
    And the message "Please enter a valid sponsor start date." is displayed
    And the message "Please enter a valid sponsor email address." is displayed
    And Exit