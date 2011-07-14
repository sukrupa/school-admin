Feature: Authentication Log In

  Scenario: Invalid password
    Given I am on Sukrupa Page
    When I enter the username "admin"
    And I enter the password "password123"
    And I Login
    Then the message "Invalid username and password" is displayed

  Scenario: Invalid username
    Given I am on Sukrupa Page
    When I enter the username "username123"
    And I enter the password "password"
    And I Login
    Then the message "Invalid username and password" is displayed


Scenario: Successful login and logoff
    Given I am on Sukrupa Page
    When I enter the username "admin"
    And I enter the password "password"
    And I Login
    Then List of Students should be displayed
    And Logout should be displayed
    When I logout
    Then the message "You are logged out." is displayed
    And Exit


