   @Login
Feature: Search students by sponsor

  Scenario: Search for Sponsor by full name
    Given I am on the Search Students by Sponsor page
    When I enter sponsor name "Tim"
    And I click "Search" submit button
    Then Abhishek should be displayed
    And Anok should be displayed

  Scenario: Search for Sponsor by partial name
    Given I am on the Search Students by Sponsor page
    When I enter sponsor name "T"
    And I click "Search" submit button
    Then Abhishek should be displayed
    And Anok should be displayed

  Scenario: Search for Sponsor using lower and upper cases
    Given I am on the Search Students by Sponsor page
    When I enter sponsor name "TiM"
    And I click "Search" submit button
    Then Abhishek should be displayed
    And Anok should be displayed

  Scenario: Search for Sponsor whose names share letters
    Given I am on the Search Students by Sponsor page
    When I enter sponsor name "i"
    And I click "Search" submit button
    Then Abhishek should be displayed
    And Anok should be displayed
    And Armugam should be displayed
    And "Bhavani" should not be displayed

  Scenario: Search for null entry where sponsors exist
    Given I am on the Search Students by Sponsor page
    When I enter sponsor name ""
    And I click "Search" submit button
    Then Armugam should be displayed
    And Abhishek should be displayed
    And Anok should be displayed
    And Bhavani should be displayed

  Scenario: Search for non-existent sponsor
    Given I am on the Search Students by Sponsor page
    When I enter sponsor name "Jlo"
    And I click "Search" submit button
    Then the message "No Sponsors found" should be displayed


