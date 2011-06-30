@Login
Feature: Student family status

    @OnStudentBhavaniProfileEditPage
    Scenario: Edit student family status and verify
    When I choose family status "Single"
    And I click "Save" button
    Then "updated successfully" should be displayed
    And "Single" should be displayed