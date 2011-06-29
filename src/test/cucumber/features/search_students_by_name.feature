@Login
Feature: Search students by name

  @OnSearchStudentPage
  Scenario: Search for "Anok"
    When I fill in the name with "Anok"
    And I click "Search" submit button

    Then "Anok" should be displayed
    And "Abhishek" should not be displayed
