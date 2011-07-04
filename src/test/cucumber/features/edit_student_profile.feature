@Login
Feature: Edit student profile

@OnListOfStudentsPage
     Scenario: Add/Edit sponsor, and verify other student details are unchanged
     When I choose "Bhavani" from student list
     And I "Edit" the form
     And I enter "Peter Murray" as the Sponsor
     And I click "Save" button
     Then "Student record updated successfully" should be displayed

     And the Student Record: Bhavani page is displayed
     And "Peter Murray" is displayed under Sponsor

     When I "View Students" in the sidebar
     And I choose "Bhavani" from student list
     Then "A+" is displayed under Academic Performance
     And "Excellent" is displayed under Disciplinary
     And "Existing Student" is displayed under Student Status
     And "Peter Murray" is displayed under Sponsor

@OnListOfStudentsPage
     Scenario:Adding a note to student profile
     When I choose "Bhavani" from student list
     And I "Edit" the form
     And I enter "This is a note" as Notes
     And I "Add a Note" to the form
     Then "Note Added Successfully" should be displayed
     And "This is a note" should be displayed in the list of notes

@OnListOfStudentsPage
     Scenario: Edit student's status successfully
     When I choose "Bhavani" from student list
     And I "Edit" the form
     And I select "Existing Student" as the Student Status
     And I click "Save" button
     Then "Student record updated successfully" should be displayed
     And the Student Record: Bhavani page is displayed
     And "Existing Student" is displayed under Student Status

@OnListOfStudentsPage
     Scenario:Verify edit of family status success
     When I choose "Bhavani" from student list
     And I "Edit" the form
     And I select "Single" as the Family Status
     And I click "Save" button
     Then "Student record updated successfully" should be displayed
     And the Student Record: Bhavani page is displayed
     And "Single" is displayed under Family Status