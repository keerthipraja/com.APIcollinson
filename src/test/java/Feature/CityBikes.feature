Feature: As a biker I would like to know the exact location
  of city bikes around the world in a given application.

  Scenario Outline:
    Given As a user I set api endpoint params
    When get http method is sent
    Then status code is "200"
    Then Assert the responses includes the following "<key>" and "<value>"

    Examples:
      | key                         | value     |
      | networks.location.city      | Frankfurt |
      | networks.location.country   | DE        |
      | networks.location.latitude  | 50.1072   |
      | networks.location.longitude | 8.66375   |