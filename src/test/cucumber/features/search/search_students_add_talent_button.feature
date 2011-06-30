@Login
Feature: search students add talent button

    Scenario: Add acting to student search criteria
        Given I am on the Student Search page
        When I click "addTalent"
        Then "chosenTalents" should contain ""
        And "chosenTalents" should not contain "Acting"

        When I select "Acting" from "availableTalents"
        And I click "addTalent"
        Then "chosenTalents" should contain "Acting"

#        When i chose another talent acting form talentpool
#        and click and
#        and chose another talent
#        and click add
#        then all should be contained

