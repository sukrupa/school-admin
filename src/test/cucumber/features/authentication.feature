Feature: Authentication Log In

  Scenario: Successful login
    Given I am on Sukrupa Page
    When I enter the username "admin"
    And I enter the password "password"
    And I select Login
    Then List of Students should be displayed
    And Logout should be displayed


  Scenario: Invalid password
    Given I am on Sukrupa Page
    When I enter the username "admin"
    And I enter the password "password123"
    And I select Login
    Then the message "Invalid username and password" should be displayed

  Scenario: Invalid username
    Given I am on Sukrupa Page
    When I enter the username "username123"
    And I enter the password "password"
    And I select Login
    Then the message "Invalid username and password" should be displayed

  @Login
  Scenario: Logging off
    When I logout
    Then the message "You are logged out." should be displayed
