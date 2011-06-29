@Login
Feature: Add New Event
    @OnCreateEventPage
    Scenario:  Create an event
        When I enter "Dancing" as the "title"
        And I enter "13-04-2011" as the "date"
        And I enter "Event description" as the "description"
        And I enter "64262" as the "attendees"
        And I submit the "save" form
        Then "Event: Dancing" should be displayed

    @OnCreateEventPage
    Scenario: Create an event with all options
        When I enter "MMA" as the "title"
        And I enter "13-4-2011" as the "date"
        And I enter "Event description..." as the "description"
        And I enter "01:30" as the "startTime"
        And I enter "03:30" as the "endTime"
        And I click "endTimePm"
        And I enter "Colloseum" as the "venue"
        And I enter "Santa" as the "coordinator"
        And I enter "BYOB" as the "notes"
        And I enter "64262" as the "attendees"
        And I submit the "Save" form
        Then "Event: MMA" should be displayed
        And "13-04-2011" should be displayed
        And "Event description..." should be displayed
        And "1:30 AM" should be displayed
        And "03:30 PM" should be displayed
        And "Colloseum" should be displayed
        #And "Santa" should be displayed
        And "BYOB" should be displayed
        And "Anok" should be displayed

    #Scenario: Clear create new event form contents
