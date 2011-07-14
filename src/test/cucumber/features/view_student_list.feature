
Feature: View student list
    @Login
     Scenario Outline: View List of Students in Sukrupa
     When I navigate to the View Students page
     Then <studentName> should be displayed
     And Exit

     Examples:
        |studentName|
        |Anok       |
        |Abhishek   |
        |Haripriya  |