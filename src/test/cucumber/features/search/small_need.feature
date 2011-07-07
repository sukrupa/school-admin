@Login
Feature: Add, Edit and Delete Big Needs


  Scenario: Small Needs List is Displayed
    Given I am on List of Students page
    When I navigate to Small Needs page
    Then Small Needs should be displayed
    And a "School Uniform" costing "5000" should be displayed
    And a "Books" costing "8000" should be displayed
    And a "5000" costing "School Uniform" should not be displayed

#  Scenario: Add item to Big Need list
#    Given I am on the Big Needs page
#    When I enter Refrigerator as Item
#    And I enter 20000 as Cost
#    And I "Add" the form
    #TODO: Ben/Nishi 2011-07-04 WIP
    #Then Refrigerator added should be displayed
 #   Then a "Refrigerator" costing "20000" should be displayed
