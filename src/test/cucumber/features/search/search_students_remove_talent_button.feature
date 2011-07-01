@Login
Feature: search students add talent button

    Scenario: Add acting to student search criteria
        Given I am on the Student Search page
        When I select "Acting" from "availableTalents"
        And I click "addTalent"
        Then "chosenTalents" should contain "Acting"

        When I click "removeTalent"
        Then "chosenTalents" should not contain "Acting"

