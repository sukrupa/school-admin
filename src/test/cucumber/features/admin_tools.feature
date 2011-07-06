@Login
Feature: admin tools on school.sukrupa.org
    Scenario: View admin menu
        Given I am on the View Students page
        When I click "Tools" link
        Then Admin Tools should be displayed

        When I click "Annual Class Update" link
        Then Annual Class Update should be displayed