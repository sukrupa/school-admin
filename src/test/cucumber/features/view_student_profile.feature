@Login
Feature: View student records

@OnListOfStudentsPage
     Scenario: View a student profile
     When I choose "Bhavani" from student list
     Then "Bhavani" is displayed under Name
     And "A+" is displayed under Academic Performance
     And "Excellent" is displayed under Disciplinary
     And "Musical Instrument" is displayed under Talents
     And "Existing Student" is displayed under Student Status

     Scenario: View another student profile
     When I "View Students" in the sidebar
     And I choose "Haripriya" from student list
     Then "Haripriya" is displayed under Name
     And "Female" is displayed under Gender
     And "20-03-1997" is displayed under Date Of Birth
     And "Born in India" is displayed under Background
     And "Spice Girls, Sports Day, Annual Day" is displayed under Events

@OnListOfStudentsPage
    Scenario: View student's photo on the profile
    When I choose "Bhavani" from student list
     Then an image for "Bhavani" with student ID "SK20090080" is displayed

    When I "View Students" in the sidebar
    When I choose "Anok" from student list
    Then an image for "Anok" with student ID "64262" is displayed



