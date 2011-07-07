    @Login
    Feature: Add student record

     Scenario: Verify that the student registration page is displayed
     Given I am on the View Students page
     When I navigate to the Add New Student page
     Then the Register a student page is displayed

     Scenario: Add new student details
     Given I am on the Add New Student page
     When I enter SK2010082011 as studentId
     And I enter Yael as name
     And I enter 06-03-1982 as dateOfBirth
     And I select Female as gender
     And I register the student
     Then the Edit Student Record: Yael page is displayed

     Scenario: Return from register a student page without adding a new student
     Given I am on the Add New Student page
     When I cancel the student registration
     Then the List of Students page is displayed

     Scenario: Display error messages when fields are incomplete
     Given I am on the Add New Student page
     When I register the student
     Then the message "Missing Student ID. Please re-enter." is displayed
     Then the message "Missing Student Name. Please re-enter." is displayed
     Then the message "Please enter a valid date format." is displayed
     Then the message "Please select a gender." is displayed

    Scenario: Display error message when New student with existing ID is entered for registration
     Given I am on the Add New Student page
     When I enter SK2010082022 as studentId
     And I enter Yael as name
     And I enter 06-03-1982 as dateOfBirth
     And I select Female as gender
     And I register the student
     Then the Edit Student Record: Yael page is displayed

     Given I am on the Add New Student page
     And I enter SK2010082022 as studentId
     And I enter Jack as name
     And I enter 12-12-1290 as dateOfBirth
     And I select Male as gender
     And I register the student
     Then the message "Student with the same ID already exists." is displayed