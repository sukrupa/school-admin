
Feature: Edit parent details
@Login

    Scenario: Select student to edit parent details
        Given I am on Sukrupa Page
        When I edit student record of Bhavani
        And I enter father's name as Peter Murray
        And I enter mother's name as My Mother
        And I enter guardian's name as My Guardian
        And I enter father's education as Not Much
        And I enter mother's education as Mother Education
        And I enter guardian's education as Guardian Education
        And I enter father's contact as 123456
        And I enter mother's contact as 1234567
        And I enter guardian's contact as 12345678
        And I enter father's salary as 5000
        And I enter mother's salary as 6000
        And I enter guardian's salary as 7000
        And I select father's occupation as Coolie
        And I select mother's occupation as House Keeper
        And I select father's maritalStatus as Single
        And I select mother's maritalStatus as Married
        And I save the changes
        Then the message "Student record updated successfully" should be displayed

    Scenario: Verify that the parent details are updated
        Given I am on Sukrupa Page
        When I select the student record of Bhavani
        Then Peter Murray should be displayed
        And My Mother should be displayed
        And My Guardian should be displayed
        And Not Much should be displayed
        And Mother Education should be displayed
        And Guardian Education should be displayed
        And 123456 should be displayed
        And 1234567 should be displayed
        And 12345678 should be displayed
        And 5000 should be displayed
        And 6000 should be displayed
        And 7000 should be displayed
        And Coolie should be displayed
        And House Keeper should be displayed
        And Single should be displayed
        And Married should be displayed
        And Exit
