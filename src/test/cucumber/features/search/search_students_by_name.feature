
Feature: Search students by name

@Login
  Scenario: Search for student by entering Partial String
  Given I am on the Search Students page
    When I enter name An
    And I submit a search

    Then Anok should be displayed
    And Chandru should not be displayed


  Scenario: Search for students by entering the full name
  Given I am on the Search Students page
    When I enter name Anok
    And I submit a search

    Then Anok should be displayed
    And Haripriya should not be displayed
    And Exit