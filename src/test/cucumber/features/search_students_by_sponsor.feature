@Login
Feature: Search students by sponsor

    @OnSearchStudentsBySponsorPage
      Scenario: Search for Sponsor by full name
      When I fill in sponsorname with "Tim"
      And I click "Search" button
      Then I should see "Abhishek"

    @OnSearchStudentsBySponsorPage
      Scenario: Search for Sponsor by partial name
      When I fill in sponsorname with "T"
      And I click "Search" button
      Then I should see "Abhishek"


    @OnSearchStudentsBySponsorPage
      Scenario: Search for Sponsor using lower and upper cases
      When I fill in sponsorname with "TiM"
      And I click "Search" button
      Then I should see "Abhishek"

    @OnSearchStudentsBySponsorPage
      Scenario: Search for Sponsor whose names share letters
      When I fill in sponsorname with "i"
      And I click "Search" button
      Then I should see "Armugam"
      Then I should see "Abhishek"
      Then I should see "Anok"
      And I should not see "Bhavani"






    @OnSearchStudentsBySponsorPage
    Scenario: Search for null entry where sponsors exist

    @OnSearchStudentsBySponsorPage
    Scenario: Search for null entry where sponsors do not exist

    @OnSearchStudentsBySponsorPage
    Scenario: Search for non-existent sponsor




