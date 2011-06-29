@Login
Feature: Search Student Records
  Scenario: Search Student Record By Giving Minimum and Maximum Age

  Given I am on the Student Search page

  When I select the age from "8"
  And I select the age to "9"
  And I submit the "Search" form

  Then I should see "Armugam"
  And I should see "Abhishek"
  And I should not see "Bhavani"
  And I should not see "Chandana"
  And I should not see "Peter"
