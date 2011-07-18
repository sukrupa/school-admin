
Feature: Edit parent details
@Login

    Scenario Outline: Select student to edit parent details
        When I edit student record of Bhavani
        And I enter father's name as <FatherName>
        And I enter mother's name as <MotherName>
        And I enter guardian's name as <GuardianName>
        And I enter father's education as <FatherEducation>
        And I enter mother's education as <MotherEducation>
        And I enter guardian's education as <GuardianEducation>
        And I enter father's contact as <FatherContact>
        And I enter mother's contact as <MotherContact>
        And I enter guardian's contact as <GuradianContact>
        And I enter father's salary as <FatherSalary>
        And I enter mother's salary as <MotherSalary>
        And I enter guardian's salary as <GuardianSalary>
        And I select father's occupation as <FatherOccupation>
        And I select mother's occupation as <MotherOccupation>
        And I select father's maritalStatus as <FatherMaritalStatus>
        And I select mother's maritalStatus as <MotherMaritalStatus>
        And I save the changes
        Then the message "Student record updated successfully" should be displayed
        #And Logout
    Examples:
        |FatherName  |MotherName|FatherEducation|MotherEducation |GuardianName|GuardianEducation |FatherContact|MotherContact|GuradianContact|FatherSalary|MotherSalary|GuardianSalary|FatherOccupation|MotherOccupation|FatherMaritalStatus|MotherMaritalStatus|
        |Peter Murray|My Mother |Not Much       |Mother Education|My Guardian |Guardian Education|123456       |1234567      |12345678       |5000        |6000        |7000          |Coolie          |House Keeper    |Single             |Married            |

    Scenario Outline: Verify that the parent details are updated
        When I select the student record of Bhavani
        Then <FatherName> should be displayed
        And <MotherName> should be displayed
        And <GuardianName> should be displayed
        And <FatherEducation> should be displayed
        And <MotherEducation> should be displayed
        And <GuardianEducation> should be displayed
        And <FatherContact> should be displayed
        And <MotherContact> should be displayed
        And <GuradianContact> should be displayed
        And <FatherSalary> should be displayed
        And <MotherSalary> should be displayed
        And <GuardianSalary> should be displayed
        And <FatherOccupation> should be displayed
        And <MotherOccupation> should be displayed
        And <FatherMaritalStatus> should be displayed
        And <MotherMaritalStatus> should be displayed
        And Exit

    Examples:
        |FatherName  |MotherName|FatherEducation|MotherEducation |GuardianName|GuardianEducation |FatherContact|MotherContact|GuradianContact|FatherSalary|MotherSalary|GuardianSalary|FatherOccupation|MotherOccupation|FatherMaritalStatus|MotherMaritalStatus|
        |Peter Murray|My Mother |Not Much       |Mother Education|My Guardian |Guardian Education|123456       |1234567      |12345678       |5000        |6000        |7000          |Coolie          |House Keeper    |Single             |Married            |
