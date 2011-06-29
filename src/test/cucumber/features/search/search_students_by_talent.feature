@Login
Feature: Search students by talent

  @OnSearchStudentPage
  Scenario: No Talents Selected
    When I click "Search" submit button

    # These students have mutually exclusive talents
    Then "Anok" should be displayed
    And "Abhishek" should be displayed

    # This student has no talents
    And "Haripriya" should be displayed

  @OnSearchStudentPage
  Scenario: One Talent Selected
    When I select the talent "Sports"
    When I click "Search" submit button

    Then "Armugam" should be displayed
    And "Anok" should not be displayed

  @OnSearchStudentPage
  Scenario: More than One Talent Selected
    When I select the talent "Sports"
    And I select the talent "Acting"
    And I select the talent "Arts & Crafts"
    And I select the talent "Mimicry"
    And I select the talent "Story Telling"
    And I click "Search" submit button

    Then "Chandana" should be displayed
    And "Abhishek" should be displayed
    And "Armugam" should be displayed
    And "Chandru" should be displayed
    And "Bhavani" should not be displayed
    And "Haripriya" should not be displayed
