Feature: this feature is to order an item

  @Order
  Scenario: item order process
    Given user launches application
    When user login to application
    And checks out the order
    When submits the order
    Then oder should be placed successfully