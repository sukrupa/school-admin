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

@OnCreateEventPage
    Scenario: Create an event with all options
        When I enter "MMA" as the "title"
        And I enter "13-4-2011" as the "date"
        And I enter "Event description..." as the "description"
        And I enter "01:30" as the "startTime"
        And I enter "03:30" as the "endTime"
        And I select "endTimePm"
        And I enter "Colloseum" as the "venue"
        And I enter "John" as the "coordinator"
        And I enter "BYOB" as the "notes"
        And I enter "64262" as the "attendees"
        And I submit the "Save" form
        Then "Event: MMA" should be displayed
        And "MMA" should be displayed
        And "13-04-2011" should be displayed
        And "Wednesday" should be displayed
        And "1:30 AM" should be displayed
        And "03:30 PM" should be displayed
        And "Colloseum" should be displayed
        And "John" should be displayed
        And "Event description..." should be displayed
        And "BYOB" should be displayed
        And "Anok" should be displayed

    @OnCreateEventPage
    Scenario: Clear create new event form contents
        When I enter "MMA" as the "title"
        And I enter "13-4-2011" as the "date"
        And I enter "Event description..." as the "description"
        And I enter "01:30" as the "startTime"
        And I enter "03:30" as the "endTime"
        And I select "endTimePm"
        And I enter "Colloseum" as the "venue"
        And I enter "BYOB" as the "notes"
        And I enter "64262" as the "attendees"
        And I select "clear"
        Then "title" is blank
        And "date" is blank
        And "startTime" is blank
        And "endTime" is blank
        And "venue" is blank
        And "attendees" is blank
        And "description" is blank
        And "notes" is blank

    @OnCreateEventPage
    Scenario: Create event without mandatory fields
        When I enter "" as the "title"
        And I enter "" as the "description"
        And I enter "" as the "attendees"
        And I enter "" as the "date"
        And I submit the "Save" form
        Then the error message "Please fill in all required fields." is displayed

        When I enter "" as the "title"
        And I enter "It's freaking awesome" as the "description"
        And I enter "64262" as the "attendees"
        And I enter "22-08-2011" as the "date"
        And I submit the "Save" form
        Then the error message "Please fill in all required fields." is displayed

        When I enter "Rachel's party" as the "title"
        And I enter "" as the "description"
        And I enter "64262" as the "attendees"
        And I enter "22-08-2011" as the "date"
        And I submit the "Save" form
        Then the error message "Please fill in all required fields." is displayed

        When I enter "Rachel's party" as the "title"
        And I enter "It's freaking awesome" as the "description"
        And I enter "" as the "attendees"
        And I enter "22-08-2011" as the "date"
        And I submit the "Save" form
        Then the error message "Please fill in all required fields." is displayed

        When I enter "Rachel's party" as the "title"
        And I enter "It's freaking awesome" as the "description"
        And I enter "64262" as the "attendees"
        And I enter "" as the "date"
        And I submit the "Save" form
        Then the error message "Please fill in all required fields." is displayed

    @OnCreateEventPage
    Scenario: Fields with invalid input should give error message
        When I enter "Rachel's party" as the "title"
        And I enter "It's freaking awesome" as the "description"
        And I enter "64262" as the "attendees"
        And I enter "may 26 2011" as the "date"
        And I submit the "Save" form
        Then the error message "Invalid date" is displayed

        When I enter "Rachel's party" as the "title"
        And I enter "It's freaking awesome" as the "description"
        And I enter "64262" as the "attendees"
        And I enter "roberto" as the "date"
        And I submit the "Save" form
        Then the error message "Invalid date" is displayed


