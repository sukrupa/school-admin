
Feature: Search students by class
@Login
  Scenario: Search for male students
      Given I am on the Search Students page
      When I select gender as "Male"
      And I submit a search
      Then Peter should be displayed
      Then Anok should be displayed
      And Haripriya should not be displayed
      And Exit
