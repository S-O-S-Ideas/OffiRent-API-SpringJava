Feature: Active Office
  Scenario: Offi-provider premium activates an Office
    Given Offi-provider has premium Account
    And Offi-provider is in the Deactivated Office window
    When Offi-provider clicks in Activate Product
    Then the system change the office status to activated


  Scenario: Offi-provider has not a Premium Account
    Given Offi-provider has not premium Account
    And Offi-provider is in the Deactivated Office window
    When Offi-provider clicks in Activate Product
    Then the system show the message Resource Office can not be changed with status with value activated