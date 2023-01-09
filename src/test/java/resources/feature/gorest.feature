Feature: Testing different request on the gorest users

  Scenario Outline: Check user is created
    When User send post request with name "<name>" gender "<gender>" email "<email>" status "<status>"
    Then User should be  created and validate response code 200
    Examples:
      | name  | gender | email           | status |
      | Prime | male   | prime@gmail.com | active |

  Scenario: Get created user with userId
    When User send get request
    Then validate response code 201


  Scenario Outline:Update created user with userId
    When User send update request with name "<name>" gender "<gender>"  email "<email>" status "<status>"
    Then User should be updated and validate status code 200
    Examples:
      | name  | gender | email           | status |
      | Prime | male   | prime@gmail.com | active |

  Scenario: Delete created user
    When User send delete request
    Then User should be deleted and validate response code 204
