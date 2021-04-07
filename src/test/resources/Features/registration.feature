Feature: Register site
  Testing the register feature.

  Scenario Outline: Register user
    Given Navigated to the site
    And write an <email>
    And write an <username>
    And write a password
    When press Sign Up button
    Then <message> is visible for user
    Examples:
      | email    | username                     | message                                                                              |
      | email    | username                     | approved message                                                                     |
      | email    | username with 110 characters | "Enter a value less than 100 characters long"                                        |
      | email    | taken username               | "Another user with this username already exists. Maybe it's your evil twin. Spooky." |
      | no email | username                     | "Please enter a value"                                                               |
