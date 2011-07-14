
Feature: Search students by sponsor

   @Login
  Scenario: Search for Sponsor by full name
    Given I am on the Search Students by Sponsor page
    When I enter sponsor name Tim
    And I submit a search
    Then Abhishek should be displayed
    And Anok should be displayed

  Scenario: Search for Sponsor whose names share letters
    Given I am on the Search Students by Sponsor page
    When I enter sponsor name i
    And I submit a search
    Then Abhishek should be displayed
    And Anok should be displayed
    And Armugam should be displayed
    And Bhavani should not be displayed

  Scenario: Search without entering a sponsor name
    Given I am on the Search Students by Sponsor page
    When I submit a search
    Then Armugam should be displayed
    And Abhishek should be displayed
    And Anok should be displayed
    And Bhavani should be displayed

  Scenario: Search for non-existent sponsor
    Given I am on the Search Students by Sponsor page
    When I enter sponsor name Jlo
    And I submit a search
    Then the message "No Sponsors found" should be displayed
    And Exit


