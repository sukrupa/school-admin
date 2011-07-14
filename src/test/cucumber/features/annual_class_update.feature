@Login
Feature: Annual Class Update

    Scenario: Annual Class Update
    Given I am on the Tools page
    When I navigate to the Annual Class Update page
    Then Annual Class Update should be displayed
    And the button Update students must be displayed

    Scenario: Update students Button
    Given I am on the Tools page
    When I navigate to the Annual Class Update page
    And I update students
    Then Annual Class Update: Confirmation should be displayed

    Scenario: Reject update students
    Given I am on the Tools page
    When I navigate to the Annual Class Update page
    And I update students
    And I select No
    Then Admin Tools should be displayed

    Scenario: Confirm update students
    Given I am on the Tools page
    When I navigate to the Annual Class Update page
    And I update students
    And I select Yes
    Then Annual Class Update: Success should be displayed

    Scenario: Check update students success
    Given I am on the View Students page
    When I navigate to Abhishek profile
    Then 5 Std should be displayed
    Given I am on the View Students page
    When I navigate to Anok profile
    Then 7 Std should be displayed


    

