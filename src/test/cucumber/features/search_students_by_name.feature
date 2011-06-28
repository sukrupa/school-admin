@Login
Feature: Search students by name

  @OnSearchStudentPage
  Scenario: Search for "Anok"
    When I fill in the "name" text box with "Anok"
    And I click "Search" submit button

    Then I should see "Anok"
    And I should not see "Abhishek"
