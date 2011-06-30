@Login
Feature: Edit parent details

    @OnViewStudentPage
    Scenario: Select student to edit parent details
       When I select "Bhavani" to edit
       And I fill in the "father.name" with "FatherName"
       And I fill in the "mother.name" with "MotherName"
       And I fill in the "father.education" with "FatherEducation"
       And I fill in the "mother.education" with "MotherEducation"
       And I fill in the "guardian.name" with "GuardianName"
       And I fill in the "guardian.education" with "GuardianEducation"
       And I fill in the "father.contact" with "FatherContact"
       And I fill in the "mother.contact" with "MotherContact"
       And I fill in the "guardian.contact" with "GuradianContact"
       And I fill in the "father.salary" with "FatherSalary"
       And I fill in the "mother.salary" with "MotherSalary"
       And I fill in the "guardian.salary" with "GuardianSalary"
       And I select "FatherOccupation" as "father.occupation"
       And I select "MotherOccupation" as "mother.occupation"
       And I select "FatherMaritalStatus" as "father.maritalStatus"
       And I select "MotherMaritalStatus" as "mother.maritalStatus"
       And I click "save" button
       Then "updated successfully" should be displayed

       |FatherName  |MotherName|FatherEducation|MotherEducation |GuardianName|GuardianEducation |FatherContact|MotherContact|GuradianContact|FatherSalary|MotherSalary|GuardianSalary|FatherOccupation|MotherOccupation|FatherMaritalStatus|MotherMaritalStatus|
       |Peter Murray|My Mother |Not Much       |Mother Education|My Guardian |Guardian Education|123456       |1234567      |12345678       |5000        |6000        |7000          |Coolie          |House Keeper    |Single             |Married            |

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
        Then "Coolie" should be displayed
        Then "House Keeper" should be displayed
        Then "Single" should be displayed
        Then "Married" should be displayed





