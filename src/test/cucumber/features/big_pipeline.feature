@Login
Feature: Add, Edit and Delete Items in Big Needs

  Scenario: Page exists
    Given I am on the "Big Needs" Page
    Then "Big Needs" should be displayed

  Scenario: Big Needs List is Displayed
     Given I am on the "Big Needs" Page
     Then "Item" should be displayed
     And "Cost" should be displayed

     And "Power Generator" should be displayed
     And "50000" should be displayed

     