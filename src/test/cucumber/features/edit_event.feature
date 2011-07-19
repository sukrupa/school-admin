
Feature: Edit an Event
@Login
    Scenario: Edit an event and confirm the changes are saved
        Given I am on the View Events page
        And I navigate to the Sports Day page
        And I Edit it
        When I enter 25-03-2011 as date
        And I enter This is an edited note as notes
        And I enter This event is awesome as description
        And I Save it
        Then 25-03-2011 should be displayed
        And This is an edited note should be displayed
        And This event is awesome should be displayed

    Scenario: close browser
        Then Exit
