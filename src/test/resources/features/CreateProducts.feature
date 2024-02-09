@PlatziAPI
Feature: Validate the creation of product

  Scenario Outline: Validate the creation of product and Negative test cases
    Given access to endpoint "/products"
    When create a new product using jsonFile
      | jsonFileName   |
      | <jsonFileName> |
    Then Validate that if the product was "<created>"
    Examples:
      | jsonFileName                        | created |
      | newProduct.json                     | true    |
      | newProductWithEmptyTitle.json       | false   |
      | newProductWithEmptyDescription.json | false   |
      | newProductWithNegativePrice.json    | false   |
      | newProductWithEmptyImages.json      | false   |

  Scenario Outline: Filter the products within 2 random prices and
  validate the returned products fall between the given prices
    Given access to endpoint "/products"
    When get all products having price_min and price_max set on
      | price_min   | price_max   |
      | <price_min> | <price_max> |
    Then check if endpoint provide products having price between the given prices "<price_min>", "<price_max>"
    Examples:
      | price_min | price_max |
      | 10        | 100       |
      | 90        | 100       |
#      | 0         | 5         | edge case
      | 500       | 500       |
