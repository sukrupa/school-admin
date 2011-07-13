@Login
Feature: View student records

     Scenario: View a student profile
     Given I am on the View Students page
     When I select the student record of Bhavani
     Then Bhavani is displayed under Name
     And A+ is displayed under Academic Performance
     And Excellent is displayed under Disciplinary
     And Musical Instrument is displayed under Talents
     And Existing Student is displayed under Student Status

     Scenario: View another student profile
     When I navigate to the View Students page
     And I select the student record of Haripriya
     Then Haripriya is displayed under Name
     And Female is displayed under Gender
     And 20-03-1997 is displayed under Date Of Birth
     And Born in India is displayed under Background
     And Spice Girls, Sports Day, Annual Day is displayed under Events

    Scenario: View student's photo on the profile
    Given I am on the View Students page
    When I select the student record of Bhavani
    Then an image for Bhavani with student ID SK20090080 is displayed

    When I navigate to the View Students page
    When I select the student record of Anok
    Then an image for Anok with student ID 64262 is displayed

    # Currently under development
    #Scenario: View sponsor details on student profile
    #Given I am on the View Students page
    #When I select the student record of Anok
    #Then Tim is displayed under Sponsor
    #And Tim@gmail.com is displayed under Sponsor Email
    #And 13-07-2011 is displayed under Sponsor Start Date

