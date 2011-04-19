Feature: Authentication

  Scenario: Successful login
    Given I am on Sukrupa Page
    When I fill in the username with "admin"
    And I fill in the password with "password"
    And I click "Login" submit button
    Then I should see "Anok"
    And I should see "Abhishek"

  Scenario: Invalid password
    Given I am on Sukrupa Page
    When I fill in the username with "admin"
    And I fill in the password with "password123"
    And I click "Login" submit button
    Then I should see "Invalid username and password"

  Scenario: Invalid username
    Given I am on Sukrupa Page
    When I fill in the username with "username123"
    And I fill in the password with "password"
    And I click "Login" submit button
    Then I should see "Invalid username and password"

  @Login
  Scenario: Logging off
    When I click "Logout" link
    Then I should see "You are logged out."