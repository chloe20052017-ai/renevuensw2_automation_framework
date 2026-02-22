@ui
Feature: Vehicle stamp duty calculator

  Scenario Outline: Calculate stamp duty for a vehicle amount
    Given I open the vehicle stamp duty page
    When I open the Revenue NSW calculator
    And I select business use "Yes"
    And I enter vehicle amount "<vehicle_amount>"
    And I calculate the stamp duty
    Then the calculation popup is displayed

    Examples:
      | vehicle_amount |
      | 45000          |
