@Login
Feature: Add, Edit and Delete Big Needs

  Scenario: Big Needs List is Displayed
    Given I am on the Big Needs page
    Then Big Needs should be displayed
    And a "Power Generator" costing "50000" should be displayed
    And a "50000" costing "Power Generator" should not be displayed

  Scenario: Add item to Big Need list
    Given I am on the Big Needs page
    When I enter Refrigerator as Item
    And I enter 20000 as Cost
    And I "Add" the form
    #TODO: Ben/Nishi 2011-07-04 WIP
    #Then Refrigerator added should be displayed
    Then a "Refrigerator" costing "20000" should be displayed

  Scenario: Delete item from the Big Need list
    Given I am on the Big Needs page
    When I delete the "Power Generator"
    #TODO: Ben/Nishi 2011-07-04 WIP
    #Then Power Generator deleted should be displayed
    And a "Power Generator" should not be displayed

  #Scenario: Edit item in the Big Need list
