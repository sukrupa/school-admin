
Feature: View Event
@Login
    Scenario: View an event
        Given I am on the View Events page
        When I navigate to the Annual Day page
        Then Event: Annual Day should be displayed
        And Annual Day should be displayed
        And 20-02-2011 should be displayed
        And Sunday should be displayed
        And This is a event description. It is a test. should be displayed
        And Exit
        #And Anok, Chandru, Haripriya should be displayed
