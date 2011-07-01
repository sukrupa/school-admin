@Login
Feature: View student profile with photo

@OnListOfStudentsPage
     Scenario: Click on the student name and see the student profile with photo
     When I choose "Bhavani" from student list
     Then image "SK20090080" is displayed

     When I "View Students" in the sidebar
     When I choose "Anok" from student list
     Then image "64262" is displayed


