
Feature: search students add talent button

@Login
    Scenario: Adding a talent without selecting it
        Given I am on the Search Students page
        When I Add the talent
        Then Chosen list of talents should now be empty
        And Chosen list of talents should not contain Acting

    Scenario: Add acting and dancing to student search criteria
        Given I am on the Search Students page
        When I select Acting from list of talents
        And I Add the talent
        And I select Dancing from list of talents
        And I Add the talent
        Then Chosen list of talents should now contain Acting
        And Chosen list of talents should now contain Dancing

    Scenario: Remove acting from selected talents of student
        Given I am on the Search Students page
        When I select Acting from list of talents
        And I Add the talent
        Then Chosen list of talents should now contain Acting
        When I Remove the selected talent
        Then Chosen list of talents should not contain Acting

    Scenario: Clear acting and dancing from selected talents for student
        Given I am on the Search Students page
        When I select Acting from list of talents
        And I Add the talent
        And I select Dancing from list of talents
        And I Add the talent
        When I Clear the chosen list of talents
        Then Chosen list of talents should not contain Acting
        And Chosen list of talents should not contain Dancing
        And Exit