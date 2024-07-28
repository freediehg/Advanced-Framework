Feature: Validate login functionality
  Scenario: Valid login functionality with valid credentials
    Given User has launched demo site url
    And User has entered valid credentials
      | UserName   | Password |
      | mngr534537 |dAdAmAt   |
    And User clicks on login
#    Then User verifies he is on login page

#  Scenario: Valid login functionality with invalid credentials
#    Given User has launched demo site url
#    And User has entered invalid credentials
#      | UserName   | Password |
#      | mngr534538 | dAdAmAt  |
#    And User clicks on login
#    Then User verifies he is on login page