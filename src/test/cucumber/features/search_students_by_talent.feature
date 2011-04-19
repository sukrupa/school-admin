@Login
Feature: Search students by talent

  @OnSearchStudentPage
  Scenario: No Talents Selected
    When I click "Search" submit button

    # These students have mutually exclusive talents
    Then I should see "Anok"
    And I should see "Abhishek"

    # This student has no talents
    And I should see "Haripriya"

  @OnSearchStudentPage
  Scenario: One Talent Selected
    When I select the talent "Sports"
    When I click "Search" submit button

    Then I should see "Armugam"
    And I should not see "Anok"

  @OnSearchStudentPage
  Scenario: More than One Talent Selected
    When I select the talent "Sports"
    And I select the talent "Acting"
    And I select the talent "Arts & Crafts"
    And I select the talent "Mimicry"
    And I select the talent "Story Telling"
    And I click "Search" submit button
    Then I should see "Chandana"
    And I should see "Abhishek"
    And I should see "Armugam"
    And I should see "Chandru"
    And I should not see "Bhavani"
    And I should not see "Haripriya"