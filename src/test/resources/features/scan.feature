Feature: scan

  Scenario: 1 Valid Login
    Given I am a user on the website
    When I log in using valid credentials
    Then I should be logged in

  Scenario: 2 Invalid Login
    Given I am a user on the website
    When I log in using invalid credentials
    Then I should not be logged in


  Scenario: 3 Product Search
    Given I am a logged in user on the website
    When I search for a product
    And I select the first product in the list
    Then I should see the product details

  Scenario: 4 Add product to cart
    Given I am a logged in user on the website
    And my shopping cart is empty
    When I view the details of a product
    And I choose to buy the product
    Then my shopping cart should contain 1 item

  @test
  Scenario Outline: 5 Add multiple products to cart
    Given I am a logged in user on the website
    And my shopping cart is empty
    When I add <num-products> products to my shopping cart
    Then my shopping cart should contain <num-products> items
    Examples:
      | num-products |
      |    3 |
      |    5 |
      |    10 |


  Scenario: 6 Removing a product from cart
    Given I am a logged in user on the website
    And my shopping cart has 2 products
    When I remove the first product in my cart
    Then my shopping cart should contain 1 item