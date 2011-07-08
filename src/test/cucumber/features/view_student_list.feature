@Login
Feature: View student list

     Scenario Outline: View List of Students in Sukrupa
     When I navigate to the View Students page
     Then <studentName> should be displayed

     Examples:
        |studentName|
        |Anok       |
        |Abhishek   |
        |Haripriya  |