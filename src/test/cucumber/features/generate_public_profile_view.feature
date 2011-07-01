@Login
Feature: Generate public profile view


        @OnStudentMasterProfilePage
        Scenario: select 'Generate profile view' link
        When I click "Generate Profile View" link
        Then "Name" should be displayed
        And "Date of Birth" should be displayed
        And "Gender" should be displayed
        And "Background" should be displayed
        And "Disciplinary" should be displayed
        And "Comments" should be displayed
        And "Caste" should not be displayed
        And "Subcaste" should not be displayed



