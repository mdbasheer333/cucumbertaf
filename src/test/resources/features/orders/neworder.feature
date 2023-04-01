Feature: this feature is to order an item new

  @Order
  Scenario: item order process1 new
    Given user launches application
    When user login to application
    And checks out the order
    When submits the order
    Then oder should be placed successfully

  @Order
  Scenario: item order process2 new
    Given user launches application
    When user login to application
    And checks out the order
    When submits the order
    Then oder should be placed successfully

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