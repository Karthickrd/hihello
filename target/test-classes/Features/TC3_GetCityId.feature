@City
Feature: City Module API automation

  Scenario Outline: Verify user Get the CityList through API
    Given User add headers to the CityList
    When User add request body for City with saved stateid
    And User send "POST" request for CityList endpoint
    Then User verify the status code is 200
    Then User Should verify the CityList response message matches "Tenkasi" and saved that id
