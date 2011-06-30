@Login
Feature: Edit parent details

    @OnViewStudentPage
    Scenario: Select student to edit parent details
       When I select "Bhavani" to edit
       And I fill in the "father.name" with "Peter Murray"
       And I fill in the "mother.name" with "My Mother"
       And I fill in the "father.education" with "Not Much"
       And I fill in the "mother.education" with "Mother Education"
       And I fill in the "guardian.name" with "My Guardian"
       And I fill in the "guardian.education" with "Guardian Education"
       And I fill in the "father.contact" with "123456"
       And I fill in the "mother.contact" with "1234567"
       And I fill in the "guardian.contact" with "12345678"
       And I fill in the "father.salary" with "5000"
       And I fill in the "mother.salary" with "6000"
       And I fill in the "guardian.salary" with "7000"
       And I click "save" button
       Then "updated successfully" should be displayed

    @OnViewStudentPage
    Scenario: Verify that the parent details are updated
        When I select "Bhavani"
        Then "Peter Murray" should be displayed
        Then "My Mother" should be displayed
        Then "My Guardian" should be displayed
        Then "Not Much" should be displayed
        Then "Mother Education" should be displayed
        Then "Guardian Education" should be displayed
        Then "123456" should be displayed
        Then "1234567" should be displayed
        Then "12345678" should be displayed
        Then "5000" should be displayed
        Then "6000" should be displayed
        Then "7000" should be displayed




