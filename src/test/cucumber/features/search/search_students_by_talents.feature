@Login
Feature: Search students by talent

  Scenario: No Talents Selected
    Given I am on the Search Students page
    When I click "Search" submit button

    # These students have mutually exclusive talents
    Then Anok should be displayed
    And Abhishek should be displayed

    # This student has no talents
    And Haripriya should be displayed

  Scenario: One Talent Selected
    Given I am on the Search Students page
    When I select the talent "Sports"
    When I click "Search" submit button

    Then Armugam should be displayed
    And "Anok" should not be displayed

  Scenario: More than One Talent Selected
    Given I am on the Search Students page
    When I select the talent "Sports"
    And I select the talent "Acting"
    And I select the talent "Arts & Crafts"
    And I select the talent "Mimicry"
    And I select the talent "Story Telling"
    And I click "Search" submit button

    Then Chandana should be displayed
    And Abhishek should be displayed
    And Armugam should be displayed
    And Chandru should be displayed
    And "Bhavani" should not be displayed
    And "Haripriya" should not be displayed
