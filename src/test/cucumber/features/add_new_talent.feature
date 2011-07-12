@Login
Feature: Add new talent

    Scenario: Add new talent to list of existing talents
        Given I am on the Tools page
        When I navigate to the Add New Talent page
        And I enter firebreathing as description 
        And I Add the new talent
        Then the message "Firebreathing added successfully" is displayed
        






   