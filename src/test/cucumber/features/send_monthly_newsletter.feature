
Feature: Send Monthly Newsletter

@Login
  Scenario: Navigate to Send Newsletter Page
    Given I am on the Send Newsletter page
    Then Send Newsletter should be displayed
    And To should default to sukrupa.test@gmail.com
    And Bcc should be displayed
    And Subject should be displayed
    And Comments should be displayed


  Scenario: Send Newsletter Without Attachment
    Given I am on the Send Newsletter page
    When I enter sukrupa.test@gmail.com as to
    And I enter Monthly Newsletter as subject
    And I send the mail
    Then Thank you should be displayed

  Scenario: Bcc field is pre-populated With Mailing List
    Given I am on the Send Newsletter page
    Then a "sukrupa.test@gmail.com;oracle.cstrike@yahoo.com;" should be displayed as pre-populated "bcc"
    Then Exit

#These tests only work on windows
  #Scenario: Send Newsletter With Attachment
   # Given I am on the Send Newsletter page
    #When I enter sukrupa.test@gmail.com as to
    #And I enter Monthly Newsletter as subject
    #And I attach a file
    #And I send the mail
    #Then Thank you should be displayed

  #Scenario: Remove the attached file
    #Given I am on the Send Newsletter page
    #When I enter sukrupa.test@gmail.com as to
    #And I attach a file
    #When I click "Remove" button
    #Then the file entry is cleared
