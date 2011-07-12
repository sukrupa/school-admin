@Login
Feature: Add, Edit and Delete Big Needs

  Scenario: Big Needs List is Displayed
    Given I am on the Big Needs page
    Then Big Needs should be displayed
    And a "Power Generator" costing "50000" should be displayed with priority "1"
    And a "50000" costing "Power Generator" with priority "1" should not be displayed
    And a "6" should be displayed as pre-populated "priority"


  Scenario: Add item to Big Need list
    Given I am on the Big Needs page
    When I enter Refrigerator as Item
    And I enter 20000 as Cost
    And I add the need
    Then Refrigerator should be displayed
    Then a "Water Purifier" costing "25000" should be displayed with priority "4"

  Scenario: Delete item from the Big Need list
    Given I am on the Big Needs page
    When I delete the "Power Generator"
    Then "Power Generator" should not be displayed

  Scenario: Edit item in the Big Need list
    Given I am on the Big Needs page
    When I edit the "Computer"
    And I update item name "Computer" with "Tractor"
    And I save edited need
    Then a "Tractor" costing "120000" should be displayed with priority "2"


#  Scenario: View the priority of the items
#    Given I am on the Big Needs page
#    Then Priority should be displayed
    

