Feature: this feature is to register a user

  @Registration
  Scenario: the user registration
    Given user launches application
    When register page should displayed
    And fill all the details in registration page
    When submits the details
    Then registration should be success
#    Examples:
#      | Iteration |
#      | 1         |
#      | 2         |