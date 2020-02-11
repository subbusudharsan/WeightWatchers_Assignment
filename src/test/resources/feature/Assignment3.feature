@Assignment3 @nthsmallestnumber
Feature: Dictionary

  @nthsmallestnumber
  Scenario Outline: Generate 500 random numbers & return nth smallest number
    Given generate 500 random numbers
    Then return <nth smallest> smallest number

    Examples:
      | nth smallest |
      | 5            |
      | 1            |
      | 100          |
      | 500          |
