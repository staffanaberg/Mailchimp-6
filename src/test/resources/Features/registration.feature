Feature: Mailchimp
  Try the registration out!

  Scenario: Register a new user
    Given Navigated to the site
    And write a username
    And write a password
    When press register
    Then the user is register

  Scenario: Register user with long username (100 characters+)

  Scenario: Register user with already taken username

  Scenario: Register user without email
