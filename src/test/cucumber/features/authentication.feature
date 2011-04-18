Feature: Authentication

  Scenario: Successful login
    Given I am on Sukrupa Page
    When I fill in the username with "admin"
    And I fill in the password with "password"
    And I click "Login" button
    Then I should see "Anok"
    And I should see "Abhishek"

  Scenario: Invalid password
    Given I am on Sukrupa Page
    When I fill in the username with "admin"
    And I fill in the password with "password123"
    And I click "Login" button
    Then I should see "Invalid username and password"

  Scenario: Invalid username
    Given I am on Sukrupa Page
    When I fill in the username with "username123"
    And I fill in the password with "password"
    And I click "Login" button
    Then I should see "Invalid username and password"

  Scenario: Logging off
    Given I am logged in
    When I click "Logout" link
    Then I should see "You are logged out."