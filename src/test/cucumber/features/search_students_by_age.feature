@Login
Feature: Search students by age

  @OnSearchStudentPage
  Scenario: Search for students between 10 and 15 years of age
    When I select a minimum age of "10"
    And I select a maximum age of "15"
    And I click "Search" submit button

    Then I should see "Peter"
    And I should see "Anok"
    And I should see "Haripriya"
    And I should not see "Abhishek"
