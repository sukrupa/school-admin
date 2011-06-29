@Login
Feature: Search Student Records
  Scenario: Search Student Record By Giving Minimum and Maximum Age

  Given I am on the Student Search page
  When I select the age from "8" to "9"
  And I click "Search" submit button

  Then "Armugam" should be displayed
  And "Abhishek" should be displayed
  And "Bhavani" should not be displayed
  And "Chandana" should not be displayed
  And "Peter" should not be displayed
