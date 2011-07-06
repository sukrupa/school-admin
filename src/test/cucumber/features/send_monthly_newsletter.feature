@Login
Feature: Send Monthly Newsletter

  Scenario: Navigate to Send Newsletter Page
    Given I am on the Send Newsletter page
    Then Send Newsletter should be displayed
    And To should be displayed
    And Subject should be displayed
    And Comments should be displayed

  Scenario: Send Newsletter Without Attachment
    Given I am on the Send Newsletter page
    When I enter mchamber@thoughtworks.com as the to
    And I enter Monthly Newsletter as the subject
    And I send the mail
    # Working on this step next: #536 Anita and Matt
    #Then Thank You should be displayed
