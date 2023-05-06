Feature: this feature is to order an item1

  @Order1
  Scenario: item order process1
    Given user launches application
    When user login to application

  @Order1
  Scenario: item order process2
    And checks out the order
    When submits the order1
    When submits the order2
    When submits the order3

  @Order1
  Scenario: item order process3
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