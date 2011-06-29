@Login
Feature: Search Student with Two Talents

  Scenario: Two Talents Selected

    Given I am on the Student Search page
    When I select the talent "Sports"
    And I select the talent "Acting"
    And I click "Search" submit button

    Then "Chandana" should be displayed
    And "Abhishek" should be displayed
    And "Armugam" should be displayed
    And "Chandru" should be displayed
    And "Anok" should not be displayed