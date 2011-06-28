@Login
Feature: Search students by class

  @OnSearchStudentPage
  Scenario: Search for male students
  When I select the "Male" gender
  And I click "Search" submit button

   Then I should see "Peter"
    And I should see "Anok"
    And I should not see "Haripriya"
