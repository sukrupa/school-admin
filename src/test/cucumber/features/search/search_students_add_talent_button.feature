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

    Scenario: Add acting and dancing to student search criteria
        Given I am on the Student Search page
        When I select "Acting" from "availableTalents"
        And I click "addTalent"
        Then "chosenTalents" should contain "Acting"
        When I select "Dancing" from "availableTalents"
        And I click "addTalent"
        Then "chosenTalents" should now contain "Acting"
        Then "chosenTalents" should now contain "Dancing"


#        When i chose another talent acting form talentpool
#        and click add
#        and chose another talent
#        and click add
#        then all should be contained
