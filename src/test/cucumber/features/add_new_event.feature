@Login
Feature: Add New Event
    @OnSearchCreateEventPage
    Scenario:  Create an event
        Given that I am on the create an event page
        When I fill in Title with "New event"
        And I fill in Date with "13-04-2011"
        And I fill in Description with "Event description..."
        And I fill in Attendees with "64262"
        And I click "Save"
        Then I should see the "Event: New event" page.

    Scenario: Create an event with timings
        When I fill in "Title" with "New event"
        And I fill in "Date" with "13-04-2011"
        And I fill in "Description" with "Event description..."
        And I fill in "Start Time" with "01:30"
        And I fill int "End Time" with "03:30"
        And I fill in "Attendees" with "64262"
        And I click "Save"
        Then I should see the "Event: New event" page
        And I should see the "start Time" is "1:30 AM"
        And I should see the "end Time" is "03:30 PM".