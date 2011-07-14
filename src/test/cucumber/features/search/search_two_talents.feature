
Feature: Search Student with Two Talents

@Login
  Scenario: Two Talents Selected

    Given I am on the Search Students page
    When I select the talent "Sports"
    And I select the talent "Acting"
    And I submit a search

    Then Chandana should be displayed
    And Abhishek should be displayed
    And Armugam should be displayed
    And Chandru should be displayed
    And Anok should not be displayed
    And Exit