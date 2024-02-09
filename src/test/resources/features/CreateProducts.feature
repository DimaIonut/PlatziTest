@PlatziAPI
Feature: Validate the creation of product

  Scenario Outline: Validate the creation of product
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