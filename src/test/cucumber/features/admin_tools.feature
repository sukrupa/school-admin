@Login
Feature: admin tools on school.sukrupa.org
    Scenario: View admin menu
        Given I am on the Tools page
        Then Admin Tools should be displayed

        When I navigate to the Annual Class Update page
        Then Annual Class Update should be displayed