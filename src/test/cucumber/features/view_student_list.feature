@Login
Feature: View student list

@OnListOfStudentsPage
     Scenario: View List of Students in Sukrupa
     When I "View Students" in the sidebar
     Then <studentName> is displayed in the list of students