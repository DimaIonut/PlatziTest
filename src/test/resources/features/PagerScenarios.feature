@PlatziAPI
Feature: Validating that it returns the expected amount

  Scenario Outline: Validating that it returns the expected amount
    Given access to endpoint "/products"
    When get all products having offset and limit set on
      | offset   | limit   |
      | <offset> | <limit> |
    Then check if endpoint provide the expected amount of "<limit>"
    Examples:
      | offset | limit |
      | 0      | 2     |
      | 0      | 5     |
      | 0      | 10    |
      | 0      | 20    |
      | 0      | 40    |
      | 10     | 10    |

  Scenario: Validate all products have the price larger than 0
    Given access to endpoint "/products"
    When get all products
    Then validate that all products have price larger than "0"

  Scenario Outline: Validate that the pager parameters work accurately
    Given access to endpoint "/products"
    When get all products having offset and limit set on
      | offset   | limit   |
      | <offset> | <limit> |
    Then validate that the pager parameters work accurately using "<id>"
    Examples:
      | offset | limit | id |
      | 0      | 2     | 6  |
      | 5      | 5     | 11 |
      | 10     | 10    | 16 |
      | 14     | 20    | 22 |
      | 20     | 40    | 28 |