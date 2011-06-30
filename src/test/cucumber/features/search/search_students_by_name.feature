@Login
Feature: Search students by name


  Scenario: Search for student by entering Partial String
  Given I am on the Student Search page
    When I enter name  "An"
    And I click "Search" submit button

    Then "Anok" should be displayed
    And "Chandru" should not be displayed


  Scenario: Search for students by entering the full name
  Given I am on the Student Search page
    When I enter name "Anok"
    And I click "Search" submit button

    Then "Anok" should be displayed
    And "Haripriya" should not be displayed