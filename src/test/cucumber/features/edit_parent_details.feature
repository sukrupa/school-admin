@Login
Feature: Edit parent details
    Scenario Outline: Select student to edit parent details
        When I edit student record of Bhavani
        And I enter father name as <FatherName>
        And I enter mother name as <MotherName>
        And I enter guardian name as <GuardianName>
        And I enter father education as <FatherEducation>
        And I enter mother education as <MotherEducation>
        And I enter guardian education as <GuardianEducation>
        And I enter father contact as <FatherContact>
        And I enter mother contact as <MotherContact>
        And I enter guardian contact as <GuradianContact>
        And I enter father salary as <FatherSalary>
        And I enter mother salary as <MotherSalary>
        And I enter guardian salary as <GuardianSalary>
        And I select father occupation as <FatherOccupation>
        And I select mother occupation as <MotherOccupation>
        And I select father maritalStatus as <FatherMaritalStatus>
        And I select mother maritalStatus as <MotherMaritalStatus>
        And I save the changes
        Then the message "Student record updated successfully" should be displayed
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
    Examples:
        |FatherName  |MotherName|FatherEducation|MotherEducation |GuardianName|GuardianEducation |FatherContact|MotherContact|GuradianContact|FatherSalary|MotherSalary|GuardianSalary|FatherOccupation|MotherOccupation|FatherMaritalStatus|MotherMaritalStatus|
        |Peter Murray|My Mother |Not Much       |Mother Education|My Guardian |Guardian Education|123456       |1234567      |12345678       |5000        |6000        |7000          |Coolie          |House Keeper    |Single             |Married            |
