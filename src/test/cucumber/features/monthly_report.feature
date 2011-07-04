@Login
Feature: Monthly report


Scenario: Navigate to monthly reports page
    When I navigate to Monthly Reports
    Then the "Monthly Reports" page is displayed

Scenario: Verify the correct data is displayed in monthly report list
    Given I am on the Monthly Reports page
    Then "Student Name" should be displayed
    And "Sponsor Name" should be displayed
    And "Sponsor's Email" should be displayed
