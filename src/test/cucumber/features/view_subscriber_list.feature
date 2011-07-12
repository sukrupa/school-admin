@Login
Feature: View subscriber list

    Scenario: View the list of existing subscribers on school.sukrupa.org
      When I go to "Tools" in the sidebar
      Then I should be able to "View Subscribers"
      Then "List of Subscribers" should be displayed
      #Then <subscriberName> and <subscriberEmail> should be displayed

      #      Examples:
      #  |subscriberName| subscriberEmail       |
      #  |subscriber1   | subscriber1@email.com |
      #  |subscriber2   | subscriber2@email.com |


