@Login
Feature: Add new talent

    @OnAddNewTalentPage
    Scenario: Add new talent
       When I fill in the "description" with "firebreathing"
       And I click "Add Talent" submit button
       Then I should see "added successfully"

    @OnStudentIDAbhishekProfilePage
    Scenario: Check new talent exists on StudentID 16346 edit page

