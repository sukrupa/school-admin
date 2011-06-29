@Login
Feature: Search students by class

  @OnSearchStudentPage
  Scenario: Search for students in 3 std class
    When I select the "10 Std" class
    And I click "Search" submit button

    Then "Peter" should be displayed
    And "Anok" should not be displayed
    And "Haripriya" should not be displayed
    And "Abhishek" should not be displayed
