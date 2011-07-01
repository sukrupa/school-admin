@Login
Feature: search students clear talent button

    Scenario: Clear acting and dancing from selected talents for student
        Given I am on the Student Search page
        When I select "Acting" from "availableTalents"
        And I click "addTalent"
        When I select "Dancing" from "availableTalents"
        And I click "addTalent"
        Then "chosenTalents" should now contain "Acting"
        Then "chosenTalents" should now contain "Dancing"
        When I click "clearTalents"
        Then "chosenTalents" should not contain "Acting"
        Then "chosenTalents" should not contain "Dancing"