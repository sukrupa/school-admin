@Login
Feature: Add, Edit and Delete Big Needs

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
        # Ben/Hephzibah 2011-07-08 Work in Progress
        #Then Shoes added should be displayed
        #Then a "Shoes" costing "200" should be displayed
