@Login
Feature: Generate public profile view

        Scenario: select 'Generate profile view' link
        Given I am on the View Students page
        When I choose "Bhavani" from student list
        When I click "Generate Profile View" link
        Then the Profile View page is displayed
        And Name should be displayed
        And Date of Birth should be displayed
        And Gender should be displayed
        And Background should be displayed
        And Disciplinary should be displayed
        And To should be displayed
        And Subject should be displayed
        And Send Profile should be displayed
        And Comments should be displayed
        And Caste should not be displayed
        And Subcaste should not be displayed



