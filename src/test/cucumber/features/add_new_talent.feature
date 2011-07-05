@Login
Feature: Add new talent

    Scenario: Add new talent to list of existing talents
        Given I am on the Tools page
        When I navigate to the Add New Talent page
        And I enter talent description as firebreathing
        And I add the talent
        Then the message "Firebreathing added successfully" should be displayed
        






   