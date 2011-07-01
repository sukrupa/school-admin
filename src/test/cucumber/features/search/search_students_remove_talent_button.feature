@Login
Feature: search students Remove talent button

    Scenario: Remove acting from selected talents of student
        Given I am on the Student Search page
        When I select "Acting" from "availableTalents"
        And I click "addTalent"
        Then "chosenTalents" should contain "Acting"

        When I click "removeTalent"
        Then "chosenTalents" should not contain "Acting"

