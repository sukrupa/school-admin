Feature: Authentication Log In

  Scenario: Successful login
    Given I am on Sukrupa Page
    When I enter the username "admin"
    And I enter the password "password"
    And I submit the "Login" form
    Then "List of Students" should be displayed
    And "Logout" should be displayed


  Scenario: Invalid password
    Given I am on Sukrupa Page
    When I enter the username "admin"
    And I enter the password "password123"
    And I submit the "Login" form
    Then "Invalid username and password" should be displayed

  Scenario: Invalid username
    Given I am on Sukrupa Page
    When I enter the username "username123"
    And I enter the password "password"
    And I submit the "Login" form
    Then "Invalid username and password" should be displayed

  @Login
  Scenario: Logging off
    When I logout
    Then "You are logged out." should be displayed
