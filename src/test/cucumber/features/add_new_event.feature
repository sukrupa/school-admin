@Login
Feature: Add New Event
    @OnCreateEventPage
    Scenario:  Create an event

        When I enter "Dancing" as the "title"
        And I fill in the "date" text box with "13-04-2011"
        And I fill in the "description" text box with "Event description..."
        And I fill in the "attendees" text box with "64262"
        And I submit the "save" form
        Then I should see "Event: New event"

    Scenario: Create an event with timings
        When I fill in the "title" text box with "New event"
        And I fill in the "date" text box with "13-04-2011"
        And I fill in the "description" text box  with "Event description..."
        And I fill in the "startTime" text box with "01:30"
        And I fill in the "endTime" text box with "03:30"
        And I fill in the "attendees" text box with "64262"
        And I submit the "Save" form
        Then I should see the "Event: New event" page
        And I should see the "start Time" is "1:30 AM"
        And I should see the "end Time" is "03:30 PM"

    Scenario: Clear create new event form contents
