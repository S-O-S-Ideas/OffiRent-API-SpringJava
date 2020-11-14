Feature: Search Office
  Scenario: Offi-user views all the offices
    Given Offi-user is within in the Home window
    And he want search offices
    When he clicks in the Offices button
    Then the system shows Offices window with a list of Offices and section of search

  Scenario: Offi-user search Offices for price less than <higherPrice>
    Given Offi-user is within in Offices window
    And he writes <higherPrice> in the input to filters
    When he clicks in the search button
    Then the system shows the Offices with value less or equal than <higherPrice>

  Scenario: Offi-user search Offices for price less than <higherPrice> and higher than <lowerPrice>
    Given Offi-user is within in Offices window
    And he writes <higherPrice> and <lowerPrice> in the input to filters
    When he clicks in the search button
    Then the system shows the Offices with within of range of <lowerPrice> and <higherPrice>