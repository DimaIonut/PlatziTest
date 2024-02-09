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

#  Scenario Outline: Validating that it returns the expected amount
#    Given user has access to endpoint "/booking"
#    When user creates a booking
#      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
#      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
#    Then user should get the response code 200
#    And user validates the response with JSON schema "createBookingSchema.json"
#
#    Examples:
#      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
#      | John      | Doe      |       1200 | true        | 2021-05-05 | 2021-05-15 | Breakfast       |
#      | Jane      | Doe      |       2400 | false       | 2021-06-01 | 2021-07-10 | Dinner          |
#
#  @createBookingFromExcel
#  Scenario Outline: To create new booking using Excel data
#    Given user has access to endpoint "/booking"
#    When user creates a booking using data "<dataKey>" from Excel
#    Then user should get the response code 200
#    And user validates the response with JSON schema from Excel
#
#    Examples:
#      | dataKey        |
#      | createBooking1 |
#      | createBooking2 |
#
#  @createBookingFromJSON
#  Scenario Outline: To create new booking using JSON data
#    Given user has access to endpoint "/booking"
#    When user creates a booking using data "<dataKey>" from JSON file "<JSONFile>"
#    Then user should get the response code 200
#    And user validates the response with JSON schema "createBookingSchema.json"
#
#    Examples:
#      | dataKey        | JSONFile         |
#      | createBooking1 | bookingBody.json |
#      | createBooking2 | bookingBody.json |
