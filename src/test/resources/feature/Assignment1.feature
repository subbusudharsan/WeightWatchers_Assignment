@Assignment1 @Dictionary
Feature: Dictionary

  @Dictionary
  Scenario Outline: Verify the presence of the file & print the words & meanings
    Given verify whether the file <File Name> is present
    Then print the words and meanings

    Examples:
      | File Name                                    |
      | src/test/resources/properties/Dictionary.txt |
