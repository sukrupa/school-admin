@Login
Feature: Search students by class

  Scenario: Search for male students
      Given I am on the Search Students page
      When I select gender as "Male"
      And I click "Search" submit button

      Then Peter should be displayed
      And Anok should be displayed
      And Haripriya should not be displayed
