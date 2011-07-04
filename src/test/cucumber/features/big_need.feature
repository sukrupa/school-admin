@Login
Feature: Add, Edit and Delete Big Needs

  Scenario: Big Needs List is Displayed
    Given I am on the Big Needs page
    Then "Big Needs" should be displayed
    And "Item" should be displayed
    And "Cost" should be displayed
    And "Power Generator" should be displayed
    And "50000" should be displayed

  Scenario: Add item to Big Need list
    Given I am on the Big Needs page
    When I enter "Refridgerator" as the "itemName"
    And I enter "20000" as the "cost"
    And I submit the "Add" form
    Then "Refridgerator" should be displayed
    And "20000" should be displayed

#Scenario: Edit item in the Big Need list
