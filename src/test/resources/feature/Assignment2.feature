@Assignment3
Feature: Weight Watchers - Automation

  #Background: User launches the browser and navigates to the WeightWatchers
  # Given User navigates to the WeightWatchers website

  @WW-meetingdetails
  Scenario: Verify and print number of meetings each person has in a week
    Given User navigates to the WeightWatchers website
    And verify we land on page using page title WW (Weight Watchers): Weight Loss & Wellness Help
    And click on element Find A Studio on Home Page
    And verify we land on page using page title Find WW Studios & Meetings Near You | WW USA
    And enter zipcode 10011 in the search field
    And click on element Search on Find A Studio Page
    Then print the result of the first result and distance
    And click on the first result
    And verify displayed location with name of the first searched result that was clicked
    Then print hours of operations from the location page
    And print the number of meeting the each person has a particular day of the week
    And I close the browser