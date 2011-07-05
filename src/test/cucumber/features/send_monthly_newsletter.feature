@Login
Feature: Send Monthly Newsletter

  Scenario: Navigate to Send Newsletter Page
    Given I am on the Send Newsletter page
    Then Send Newsletter should be displayed
    And To should be displayed
    And Subject should be displayed
    And Comments should be displayed