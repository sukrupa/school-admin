@Login
Feature: View student list

@OnListOfStudentsPage
     Scenario: Verify the students are displayed in the student list
        When I "View Students" in the sidebar
     Then "Chandana" is displayed in the list of students
     And "Anok" is displayed in the list of students
     And "Bhavani" is displayed in the list of students