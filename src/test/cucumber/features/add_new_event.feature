@Login
Feature: Add New Event
    Scenario:  Create an event with mandatory fields
        Given I am on the Create an Event page
        When I enter Dancing as the title
        And I enter 13-04-2011 as the date
        And I enter Event description as the description
        And I enter 64262 as the attendees
        And I select Save
        Then Event: Dancing should be displayed
        And Dancing should be displayed
        And 13-04-2011 should be displayed
        And Wednesday should be displayed
        And Event description should be displayed
        And Anok should be displayed

