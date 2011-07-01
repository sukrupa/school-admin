@Login

Feature: Monthly report

Scenario: Monthly report link visibility

    Given I am on Sukrupa Page
    Then "Monthly Reports" should be displayed


Scenario: Navigate to monthly reports page

    Given I am on Sukrupa Page
    When I select "Monthly Reports" in the sidebar
    Then the "Monthly Reports" page is displayed

Scenario: Students Name, Sponsor Name and email are displayed on Monthly Reports Page

    Given I am on the Monthly Reports Page
    Then "Student Name" should be displayed
    And "Sponsor Name" should be displayed
    And "Sponsor's Email" should be displayed

Scenario: Monthly Reports table is populated with correct data

    Given I am on the Monthly Reports Page
    Then student "Anok" should be displayed with sponsor "Tim" and email_Id "Tim@gmail.com"


