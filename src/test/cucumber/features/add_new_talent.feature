@Login
Feature: Add new talent

    @OnAddNewTalentPage
    Scenario: Add new talent
       When I add a new talent "firebreathing"
       And I click "Add Talent" submit button
       Then "added successfully" should be displayed

    @OnSearchStudentPage
    Scenario: Check that new talent exists in talent field
        Then "Firebreathing" should be displayed in "availableTalents"

