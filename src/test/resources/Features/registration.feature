Feature: Mailchimp
  Try the registration out!


  @newUser
  Scenario: Register a new user
    Given Navigated to the site
    And write an email
    And write an username
    And write a password
    When press Sign Up button
    Then the user is register

  @longUsername
  Scenario: Register user with long username (100+ characters)
    Given Navigated to the site
    And write an email
    And write a long username, 110 characters
    And write a password
    When press Sign Up button
    Then too long username error message shows

  @takenUsername
  Scenario: Register user with already taken username
    Given Navigated to the site
    And write an email
    And write an already taken username
    And write a password
    When press Sign Up button
    Then name already exists error message shows

  @userWithoutUsername
  Scenario: Register user without email
    Given Navigated to the site
    And write no email
    And write an username
    And write a password
    When press Sign Up button
    Then no email error message shows
