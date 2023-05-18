@Address
Feature: Address Module API Automation

  @Address_Add_User
  Scenario Outline: Verify and user address to the application through api
    Given User add header and bearer authurization for accesing address endpoints
    When User add request body for add new address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "POST" request for addUserAddress endpoint
    Then User verify the status code is 200
    And User verify the addUserAddress response message matches "Address added successfully" and save the address Id

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address       | address_type |
      | Karthick   | Rajendran | 7305055527 | HappyStay |   105 |  765 |     100 |  600097 | Thoraippakkam | Work         |

  @Address_Update_User
  Scenario Outline: Verify update user address to the application through api
    Given User add header and bearer authurization for accesing address endpoints
    When User add request body for update user address with saved addressId "<first_Name>","<last_Name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipCode>","<address>" and "<address_Type>"
    And User send "PUT" request for updateUserAddress endpoint
    Then User verify the status code is 200
    Then User verify the updateUserAddress response message matches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address       | address_type |
      | Karthick   | Rajendran | 7305055527 | HappyStay |   105 |  765 |     100 |  600097 | Thoraippakkam | Work         |

  @Address_Get_User
  Scenario: Verify get user address to the application through api
    Given User add header and bearer authorization for accessing getUserAddress endpoint
    And User send "GET" request for getUserAddress endpoint
    Then User verify the status code is 200
    Then User Should verify the getUserAddress response message matches "OK"

  @Address_Delete_User
  Scenario: Verify delete user address to the application through api
    Given User add header and bearer authurization for accesing address endpoints
    When User add request body for delete user address with saved addressId
    And User send "DELETE" request for deleteUserAddress endpoint
    Then User verify the status code is 200
    Then User Should verify the deleteUserAddress response message matches "Address deleted successfully"
