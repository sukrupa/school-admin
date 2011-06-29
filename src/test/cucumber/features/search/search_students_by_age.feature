@Login
Feature: Search students by age

  @OnSearchStudentPage
  Scenario: Search for students between 10 and 15 years of age
    When I select the age from "10" to "15"
    And I click "Search" submit button

    Then "Peter" should be displayed
    And "Anok" should be displayed
    And "Haripriya" should be displayed
    And "Abhishek" should not be displayed
