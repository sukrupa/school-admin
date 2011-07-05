@Login
Feature: Search Student Records


  Scenario Outline: Search student record by table
    Given I am on the Search Students page
    When I select the talent "<talent>"
    And I select the age from "<ageFrom>" to "<ageTo>"
    And I select class as "<class>"
    And I select gender as "<gender>"
    And I select religion as "<religion>"
    And I select caste as "<caste>"
    And I select community location as "<community_location>"
    And I select family status as "<family_status>"
    And I click "Search" submit button

    Then <name> should be displayed

    Examples:
     | talent             | ageFrom | ageTo | class | gender  | religion  | caste  | community_location | family_status | name     |
     |                    | 12      | 17    | Any   | Male    | Any       | Any    | Any                | Any           | Anok     |
     | Mimicry            | 12      | 20    | Any   | Any     | Any       | Any    | Any                | Single        | Anok     |
     | Acting             | 3       | 7     | Any   | Any     | Any       | Any    | Any                | Any           | Chandana |
     |                    |         |       | Any   | Any     | Any       | Shetty | Any                | General       | Chandana |
     | Singing            |         |       | Any   | Any     | Any       | Any    | Bhuvaneshwari Slum | Any           | Chandru  |
     |                    | 5       | 8     | Any   | Any     | Any       | Shetty | Any                | Any           | Chandana |
     | Musical Instrument |         |       | Any   | Any     | Any       | Any    | Any                | Any           | Bhavani  |
