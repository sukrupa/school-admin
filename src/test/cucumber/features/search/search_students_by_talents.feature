@Login
Feature: search students add talent button

    Scenario: Add acting to student search criteria
        Given I am on the Search Students page
      #  When I click "addTalent"
      #  Then Chosen list of talents should contain ""
      #  And Chosen list of talents should not contain "Acting"

        When I select "Acting" from list of talents
        And I click "addTalent"
        Then Chosen list of talents should contain "Acting"

    Scenario: Add acting and dancing to student search criteria
        Given I am on the Search Students page
        When I select "Acting" from list of talents
        And I click "addTalent"
        And I select "Dancing" from list of talents
        And I click "addTalent"
        Then Chosen list of talents should now contain "Acting"
        And Chosen list of talents should now contain "Dancing"



    Scenario: Remove acting from selected talents of student
        Given I am on the Search Students page
        When I select "Acting" from list of talents
        And I click "addTalent" button
        Then Chosen list of talents should contain "Acting"

        When I select "removeTalent" button
        Then Chosen list of talents should not contain "Acting"



    Scenario: Clear acting and dancing from selected talents for student
        Given I am on the Search Students page
        When I select "Acting" from list of talents
        And I click "addTalent" button
        And I select "Dancing" from list of talents
        And I click "addTalent" button
        Then Chosen list of talents should now contain "Acting"
        And Chosen list of talents should now contain "Dancing"

        When I select "clearTalents" button
        Then Chosen list of talents should not contain "Acting"
        And Chosen list of talents should not contain "Dancing"