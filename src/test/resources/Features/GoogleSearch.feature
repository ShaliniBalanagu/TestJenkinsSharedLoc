@tag
Feature: Google Search
  I want to use this template for my feature file

  @tag1
  Scenario: Enter data in serach field of google serach page
    Given Google application is launched
    When I see the search field and enter data in it
    Then I validate the results displayed

  @tag2
  Scenario Outline: Title of your scenario outline
    Given Google application is launched
    When User enters data from "<Sheetname>" and "<row>" and hits enter
    Then Verify that the results are displayed

    Examples: 
      | Sheetname| row | 
      | Sheet1 |     1 | 
      | Sheet1 |     2 | 
