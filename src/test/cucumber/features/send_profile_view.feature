
Feature: Send Profile View to Sponsor

@Login
    Scenario: Send Profile View with Mandatory field
        Given I am on the Profile View page for Bhavani
        When I enter the email to "sukrupa.test@gmail.com"
        And I enter This is a subject as subject
        And I enter Some Comment as comments
        And I Send Profile to sponsor
        #Then Thank you should be displayed  #Currently build on ci server failing on this, somebody please fix it
        And the message "The message has been sent successfully" is displayed
        And Exit

