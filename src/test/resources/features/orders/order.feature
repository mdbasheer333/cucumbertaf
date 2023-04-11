Feature: this feature is to order an item

  @Order
  Scenario: item order process1
    Given user launches application
    When user login to application
    And checks out the order
    When submits the order
    Then oder should be placed successfully
    Then registration should be success

  @Order
  Scenario: item order process2
    Given user launches application
    When user login to application
    And checks out the order
    When submits the order
    Then oder should be placed successfully
    Then registration should be success

#  Scenario Template: item order process <Iteration>
#    Given user launches application
#    When user login to application
#    And checks out the order
#    When submits the order
#    Then oder should be placed successfully
#    Examples:
#      | Iteration |
#      | 1         |
#      | 2         |