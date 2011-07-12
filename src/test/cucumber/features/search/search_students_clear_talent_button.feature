@Login
Feature: search students clear talent button

    Scenario: Clear acting and dancing from selected talents for student
        Given I am on the Search Students page
        When I select "Acting" from "availableTalents"
        And I Add the talent
        And I select "Dancing" from "availableTalents"
        And I Add the talent
        Then Chosen list of talents should now contain Acting
        And Chosen list of talents should now contain Dancing
        When I click "clearTalents" button
        Then Chosen list of talents should not contain Acting
        And Chosen list of talents should not contain Dancing