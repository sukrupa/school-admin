
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
        And Exit
        #And attendees should contain 64262, 12345, 34545

   #Scenario: Editing an event by changing fields
        #Given I am on the View Events page
        #When I navigate to the Annual Day page
        #And I select Edit
        #And I enter Dancing as the title
        #And I enter 13-04-2011 as the date
        #And I enter Event description as the description
        #And I enter 64262 as the attendees
        #And I select Save
        #Then Event: Dancing should be displayed
        #Then Dancing should be displayed
        #And 13-04-2011 should be displayed
        #And Wednesday should be displayed
        #And Event description should be displayed
        #And Anok should be displayed

