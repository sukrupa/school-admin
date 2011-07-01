@Login
Feature: View student list

@OnListOfStudentsPage
     Scenario Outline: View List of Students in Sukrupa
     When I "View Students" in the sidebar
     Then "<studentName>" should be displayed

      Examples:
        |studentName|
        |Anok       |
        |Abhishek   |
        |Haripriya  |