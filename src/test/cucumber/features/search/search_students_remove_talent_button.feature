@Login
Feature: search students Remove talent button

    Scenario: Remove acting from selected talents of student
        Given I am on the Search Students page
        When I select "Acting" from "availableTalents"
        And I click "addTalent" button
        Then "chosenTalents" should contain "Acting"

        When I click "removeTalent" button
        Then "chosenTalents" should not contain "Acting"

