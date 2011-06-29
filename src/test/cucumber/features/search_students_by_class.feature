@Login
Feature: Search students by class

  @OnSearchStudentPage
  Scenario: Search for students in 3 std class
    When I select the "10 Std" class
    And I click "Search" submit button

    Then I should see "Peter"
    And I should not see "Anok"
    And I should not see "Haripriya"
    And I should not see "Abhishek"
