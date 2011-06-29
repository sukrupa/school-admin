@Login
Feature: Search Student Records
  Scenario: Search Student Record By Giving Minimum and Maximum Age

  Given I am on the Student Search page
  When I select the age from "8" to "9"
  And I click the "Search" submit button

  Then student "Armugam" is displayed
  And student "Abhishek" is displayed
  And student "Bhavani" is not displayed
  And student "Chandana" is not displayed
  And student "Peter" is not displayed
