
Feature: search students Remove talent button

@Login
    Scenario: Remove acting from selected talents of student
        Given I am on the Search Students page
        When I select "Acting" from "availableTalents"
        And I Add the talent
         Then Chosen list of talents should now contain Acting

        When I click "removeTalent" button
         Then Chosen list of talents should not contain Acting
         And Exit

