
Feature: View subscriber list
    @Login
    Scenario Outline: View the list of existing subscribers on school.sukrupa.org
      When I go to "Tools" in the sidebar
      Then I should be able to "View Current Subscribers"
      And List of Subscribers should be displayed
      And <SubscriberName> should be displayed
      And <SubscriberEmail> should be displayed


      Examples:
      |SubscriberName  |SubscriberEmail    |
      |Abhinaya        |sukrupa.test@gmail.com|

    Scenario: Delete a Subscriber from Subscriber List
      When I go to "Tools" in the sidebar
      Then I should be able to "View Current Subscribers"
      When I delete "Abhinaya" with "sukrupa.test@gmail.com" as email from List of Subscribers
      Then "Abhinaya" with email "sukrupa.test@gmail.com" should not be displayed
      And Exit


