@Login
Feature: Login Module API Automation

  Scenario: Get user logtoken from login endpoint
    Given User add Header
    When User add basic authentication for login
    And User send "POST" request for login endpoint
    Then User verify the status code is 200
    Then User Should verify the login response body firstName present as "Karthick" and get the logtoken saved
