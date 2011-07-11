@Login
Feature: Add, Edit and Delete Small Needs

    Scenario: Small Needs List is Displayed
        Given I am on the View Students page
        When I navigate to the Small Needs page
        Then Small Needs should be displayed
        And a "School Uniform" costing "5000" should be displayed with priority "1"
        And a "Books" costing "8000" should be displayed with priority "2"
        And a "5000" costing "School Uniform" with priority "3" should not be displayed

    Scenario: Add item to Small Need list
        Given I am on the Small Needs page
        When I enter Shoes as Item
        And I enter 200 as Cost
        And I enter For Anok as Comment
        And I "Add" the form
        Then the message "Added Shoes" is displayed
        Then a "Shoes" costing "200" should be displayed with priority "3"

    Scenario: Delete item from the Small Need list
        Given I am on the Small Needs page
        When I delete the "Books" with priority "2"
        Then the message "Deleted Books" is displayed
        Then a "Books" costing "8000" with priority "2" should not be displayed

    Scenario: Edit item in the Small Need list
       Given I am on the Small Needs page
       When I edit the "School Uniform"
       When I enter "Geometry Box" as Item
       And I enter "400" as Cost
       Then School Uniform edited should be displayed
       Then a "Geometry Box" costing "400" should be displayed