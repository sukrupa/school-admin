@Login
Feature: View student list

     Scenario Outline: View List of Students in Sukrupa
     Given I am on the View Students page
     When I "View Students" in the sidebar
     Then <studentName> should be displayed

     Examples:
        |studentName|
        |Anok       |
        |Abhishek   |
        |Haripriya  |