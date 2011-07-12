@Login
Feature: Send End of Sponsorship

  Scenario: I can see the end of sponsorship page
    Given I am on the Monthly Reports page
    When I navigate to the Send mail page
    Then Send Approaching End of Sponsorship should be displayed
    And To should be displayed
    And Subject should be displayed
    And Message should be displayed

  Scenario: I can send an end of sponsorship email
    Given I am on the Monthly Reports page
    And I navigate to the Send mail page
    When I enter sponsor@somewhere.com as To:
    And I enter end of sponsorship email as Subject:
    And I "send" the form
    Then the message "Thank you. Mail sent." should be displayed

  Scenario: To, Subject, and Message field should be pre-populated
    Given I am on the Monthly Reports page
    When I navigate to the Send mail page
    Then subject should contain Sukrupa sponsorship is expiring soon
    And message should contain Thanks for sponsoring.
    And to should contain Tim@gmail.com

