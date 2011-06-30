@Login
Feature: Edit parent details


    Scenario Outline: Select student to edit parent details
       When I select "Bhavani" to edit
       And I enter "father" name as "<FatherName>"
       And I enter "mother" name as "<MotherName>"
       And I enter "guardian" name as "<GuardianName>"
       And I enter "father" education as "<FatherEducation>"
       And I enter "mother" education as "<MotherEducation>"
       And I enter "guardian" education as "<GuardianEducation>"
       And I enter "father" contact as "<FatherContact>"
       And I enter "mother" contact as "<MotherContact>"
       And I enter "guardian" contact as "<GuradianContact>"
       And I enter "father" salary as "<FatherSalary>"
       And I enter "mother" salary as "<MotherSalary>"
       And I enter "guardian" salary as "<GuardianSalary>"
       And I select "<FatherOccupation>" as "father" occupation
       And I select "<MotherOccupation>" as "mother" occupation
       And I select "<FatherMaritalStatus>" as "father" maritalStatus
       And I select "<MotherMaritalStatus>" as "mother" maritalStatus
       And I click "save" button
       Then "updated successfully" should be displayed


      Examples:
        |FatherName  |MotherName|FatherEducation|MotherEducation |GuardianName|GuardianEducation |FatherContact|MotherContact|GuradianContact|FatherSalary|MotherSalary|GuardianSalary|FatherOccupation|MotherOccupation|FatherMaritalStatus|MotherMaritalStatus|
        |Peter Murray|My Mother |Not Much       |Mother Education|My Guardian |Guardian Education|123456       |1234567      |12345678       |5000        |6000        |7000          |Coolie          |House Keeper    |Single             |Married            |

Scenario Outline: Verify that the parent details are updated
        When I select "Bhavani"
        Then "<FatherName>" should be displayed
        Then "<MotherName>" should be displayed
        Then "<GuardianName>" should be displayed
        Then "<FatherEducation>" should be displayed
        Then "<MotherEducation>" should be displayed
        Then "<GuardianEducation>" should be displayed
        Then "<FatherContact>" should be displayed
        Then "<MotherContact>" should be displayed
        Then "<GuradianContact>" should be displayed
        Then "<FatherSalary>" should be displayed
        Then "<MotherSalary>" should be displayed
        Then "<GuardianSalary>" should be displayed
        Then "<FatherOccupation>" should be displayed
        Then "<MotherOccupation>" should be displayed
        Then "<FatherMaritalStatus>" should be displayed
        Then "<MotherMaritalStatus>" should be displayed

      Examples:
        |FatherName  |MotherName|FatherEducation|MotherEducation |GuardianName|GuardianEducation |FatherContact|MotherContact|GuradianContact|FatherSalary|MotherSalary|GuardianSalary|FatherOccupation|MotherOccupation|FatherMaritalStatus|MotherMaritalStatus|
        |Peter Murray|My Mother |Not Much       |Mother Education|My Guardian |Guardian Education|123456       |1234567      |12345678       |5000        |6000        |7000          |Coolie          |House Keeper    |Single             |Married            |






