
Feature: search students add talent button

@Login
    Scenario: Add acting to student search criteria
        Given I am on the Search Students page
        When I Add the talent
        Then Chosen list of talents should now be empty
        And Chosen list of talents should not contain Acting

        When I select "Acting" from "availableTalents"
        When I Add the talent
        Then Chosen list of talents should now contain Acting

    Scenario: Add acting and dancing to student search criteria
        Given I am on the Search Students page
        When I select "Acting" from "availableTalents"
        When I Add the talent
        Then Chosen list of talents should now contain Acting
        When I select "Dancing" from "availableTalents"
        When I Add the talent
        Then Chosen list of talents should now contain Acting
        Then Chosen list of talents should now contain Dancing
        Then Exit


#        When i chose another talent acting form talentpool
#        and click add
#        and chose another talent
#        and click add
#        then all should be contained

