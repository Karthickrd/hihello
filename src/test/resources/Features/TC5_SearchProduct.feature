@SearchProduct
Feature: SearchProduct Module API Automation

  Scenario Outline: Verify search product to the application through api
    Given User add header for searchProduct
    When User add request body for searchProduct "<productName>"
    And User send "POST" request for searchProduct endpoint
    Then User verify the status code is 200
    Then User Should verify the searchProduct response message matches "OK"

    Examples: 
      | productName |
      | nuts        |
