@Login
Feature: Add new talent

    @OnAddNewTalentPage
    Scenario: Add new talent
       When I fill in the "description" with "firebreathing"
       And I click "Add Talent" button
       Then "added successfully" should be displayed

    @OnSearchStudentPage
    Scenario: Check that new talent exists in talent field
        Then "Firebreathing" should be displayed in "availableTalents"

