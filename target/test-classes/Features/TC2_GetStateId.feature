@State
Feature: State Module API Automation

  Scenario: Verify User Get StateList through api
    Given User and headers for to StateList
    When User send "GET" request for StateList endpoint
    Then User verify the status code is 200
    And User verify the StateList response message matches "Tamil Nadu" and saved that id
