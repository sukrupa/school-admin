
Feature: Add New Event
@Login
    Scenario: Create an event with mandatory fields
        Given I am on the Create an Event page
        When I enter Dancing as title
        And I enter 13-04-2011 as date
        And I enter Event description as description
        And I save the changes
        Then Event: Dancing should be displayed
        And Dancing should be displayed
        And 13-04-2011 should be displayed
        And Wednesday should be displayed
        And Event description should be displayed

    Scenario: Create event with all fields
        Given I am on the Create an Event page
        When I enter MMA as title
        And I enter 13-4-2011 as date
        And I enter Event description... as description
        And I enter the start time as "01:30" pm
        And I enter the end time as "03:30" pm
        And I enter Colloseum as venue
        And I enter John as coordinator
        And I enter BYOB as notes
        And I add Anok (64262) as an attendee
        And I add Chandru (12345) as an attendee
        And I save the changes
        Then Event: MMA should be displayed
        And MMA should be displayed
        And 13-04-2011 should be displayed
        And Wednesday should be displayed
        And 1:30 PM should be displayed
        And 3:30 PM should be displayed
        And Colloseum should be displayed
        And John should be displayed
        And Event description... should be displayed
        And BYOB should be displayed
        And Anok should be displayed
        And Chandru should be displayed

    Scenario: Create event with using AM and PM radio buttons
        Given I am on the Create an Event page
        When I enter MMA as title
        And I enter 13-4-2011 as date
        And I enter Event description... as description
        And I enter the start time as "01:30" pm
        And I enter the end time as "03:30" pm
        And I click am for start time
        And I click pm for start time
        And I click am for end time
        And I save the changes
        Then Event: MMA should be displayed
        And MMA should be displayed
        And 13-04-2011 should be displayed
        And Wednesday should be displayed
        And 1:30 PM should be displayed
        And 3:30 AM should be displayed
        And Event description... should be displayed

    Scenario: Clear create new event form contents
        Given I am on the Create an Event page
        When I enter MMA as title
        And I enter 13-4-2011 as date
        And I enter Event description... as description
        And I enter the start time as "01:30" pm
        And I enter the end time as "03:30" pm
        And I enter Colloseum as venue
        And I enter BYOB as notes
        And I add Anok (64262) as an attendee
        And I clear all the fields
        Then title is blank
        And date is blank
        And startTime is blank
        And endTime is blank
        And venue is blank
        And attendingStudents is blank
        And description is blank
        And notes is blank

    Scenario: Create event without mandatory fields
        Given I am on the Create an Event page
        When I save the event
        Then the message "Please fill in all required fields." is displayed

        When I enter It is freaking awesome as description
        And I enter 22-08-2011 as date
        And I save the changes
        Then the message "Please fill in all required fields." is displayed

        When I clear all the fields
        And I enter Rachel party as title
        And I enter 22-08-2011 as date
        And I save the changes
        Then the message "Please fill in all required fields." is displayed

        When I clear all the fields
        And I enter Rachel party as title
        And I enter It is freaking awesome as description
        And I save the changes
        Then the message "Please fill in all required fields." is displayed

    Scenario: Fields with invalid input should give error message
        Given I am on the Create an Event page
        When I enter Rachel party as title
        And I enter It is freaking awesome as description
        And I add Anok (64262) as an attendee
        And I enter may 26 2011 as date
        And I save the changes
        Then the message "Invalid date" is displayed

        And I enter roberto as date
        And I save the changes
        Then the message "Invalid date" is displayed
        And Exit