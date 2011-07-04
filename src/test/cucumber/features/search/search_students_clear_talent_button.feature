@Login
Feature: search students clear talent button

    Scenario: Clear acting and dancing from selected talents for student
        Given I am on the Search Students page
        When I select "Acting" from "availableTalents"
        And I click "addTalent" button
        When I select "Dancing" from "availableTalents"
        And I click "addTalent" button
        Then "chosenTalents" should now contain "Acting"
        Then "chosenTalents" should now contain "Dancing"
        When I click "clearTalents" button
        Then "chosenTalents" should not contain "Acting"
        Then "chosenTalents" should not contain "Dancing"