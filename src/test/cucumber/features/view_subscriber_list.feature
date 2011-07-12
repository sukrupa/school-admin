@Login
Feature: View subscriber list

    Scenario Outline: View the list of existing subscribers on school.sukrupa.org
      When I go to "Tools" in the sidebar
      Then I should be able to "View Subscribers"
      And "List of Subscribers" should be displayed
      And "<SubscriberName>" should be displayed
      And "<SubscriberEmail>" should be displayed

       Examples:
        |SubscriberName  |SubscriberEmail    |
        |Roberto         |robocop@aljksaj.com|

