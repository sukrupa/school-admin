@Login
Feature: Add, Edit and Delete Items in Big Pipeline

  Scenario: Page exists
    Given I am on the "Big Needs" Page
    Then "Big Needs" should be displayed

  Scenario: Big Needs List is Displayed
     Given I am on the "Big Needs" Page
     Then "Item" should be displayed
     And "Cost" should be displayed

     And "Power Generator" should be displayed
     And "50000" should be displayed

  Scenario: Add item to Big Need List
         Given I am on the "Big Needs" Page
         When I enter "Refridgerator" as the "itemName"
         And I enter "20000" as the "costString"
         And I click the "Add" button
         Then "Refridgerator" should be displayed
         And "20000" should be displayed

