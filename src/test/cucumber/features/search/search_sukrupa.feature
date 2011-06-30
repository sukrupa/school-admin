Feature: Search on sukrupa.org

  @OnSukrupaHomepage
  Scenario: Search the Sukrupa website
  When I search for "Subscribe"
  Then the search results are displayed
