
Feature: Search students by class
@Login
  Scenario: Search for students in 3 std class
    Given I am on the Search Students page
    When I select class as "10 Std"
    And I submit a search

    Then Peter should be displayed
    And Anok should not be displayed
    And Haripriya should not be displayed
    And Abhishek should not be displayed
    And Exit
