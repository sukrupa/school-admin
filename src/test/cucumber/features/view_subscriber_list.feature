@Login
Feature: View the subscriber list

    Scenario: View the list of existing subscribers
        Given I am on the Tools page
        When I navigate to the View Subscribers page
        Then Subscribers should be displayed


