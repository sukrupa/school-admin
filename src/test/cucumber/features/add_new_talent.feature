@Login
Feature: Add new talent

    @OnAddNewTalentPage
    Scenario: Add new talent
       When I fill in the "description" with "firebreathing"
       And I click "addNewTalent" button
       And I click "OK" button
       Then I should see "added successfully"

