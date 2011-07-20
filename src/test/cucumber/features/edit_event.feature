
Feature: Edit an Event
@Login
        Scenario: Edit all of an event and confirm the changes are saved
            Given I am on the View Events page
            And I navigate to the Sports Day page
            And I Edit it

            When I enter The Most Awesome Event EVER! as title
            And I enter 01-01-2013 as date
            And I enter =) l33tn355 as description
            And I enter the start time as "12:01" pm
            And I click am for start time
            And I enter the end time as "11:59" pm
            And I enter Earth as venue
            And I enter Socar as coordinator
            And I enter We are all still here! as notes
            And I add Anok (64262) as an attendee
            And I save the changes

            Then Event: The Most Awesome Event EVER! should be displayed
            And 01-01-2013 should be displayed
            And =) l33tn355 should be displayed
            And 12:01 AM should be displayed
            And 11:59 PM should be displayed
            And Earth should be displayed
            And Socar should be displayed
            And We are all still here! should be displayed
            And Anok should be displayed

    Scenario: Edit an event and confirm the changes are saved
        Given I am on the View Events page
        And I navigate to the The Most Awesome Event EVER! page
        And I Edit it

        When I enter 25-03-2011 as date
        And I enter This is an edited note as notes
        And I enter This event is awesome as description
        And I enter Sports Day as title
        And I save the event

        Then 25-03-2011 should be displayed
        And This is an edited note should be displayed
        And Sports Day should be displayed
        And This event is awesome should be displayed

#todo
    Scenario: Cancel button should redirect to list of events page
        Given I am on the View Events page
        And I navigate to the Spice Girls page
#todo
    Scenario: Should be able to add remove and clear attendees
        Given I am on the View Events page
        And I navigate to the Spice Girls page

    Scenario: close browser
        Then Exit
