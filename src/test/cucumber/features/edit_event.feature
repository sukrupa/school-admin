
Feature: Edit an Event

@Login

    Scenario: Edit an event
        Given I am on the View Events page
        When I navigate to the Annual Day page
        And I Edit it
        Then title should contain Annual Day
        And date should contain 20-02-2011
        And description should contain This is a event description. It is a test.
        And notes should contain This is a note
