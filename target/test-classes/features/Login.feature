Feature: Validation of login credentials

  Background:
  #  Given user is navigated to HRMS application

  @regression @batch12 @smoke @sprint12 @login
  Scenario: Admin Login
    #Given user is navigated to HRMS application
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user successfully logged in

  @regression @login
  Scenario: ESS Login
    #Given user is navigated to HRMS application
    When user enters valid ess username and password
    And user clicks on login button
    Then ess user is successfully logged in

  @regression @login
  Scenario: Invalid Login
    #Given user is navigated to HRMS application
    When users enters invalid username and password
    And user clicks on login button
    Then user sees error message on the screen

  @test @login
  Scenario: Valid Username and Invalid Password
    When user enters valid username and invalid password
    And user clicks on login button
    Then user gets "Invalid credentials" error message

  @test @login
  Scenario: Invalid Username and Valid Password
    When user enters invalid username and valid password
    And user clicks on login button
    Then user gets "Invalid credentials" error message

  @test @login
  Scenario: Blank Username and Valid Password
    When user enters valid username and no password
    And user clicks on login button
    Then user gets "Password cannot be empty" error message

  @test @login
  Scenario: Valid Username and Blank Password
    When user enters no username and valid password
    And user clicks on login button
    Then user gets "Username cannot be empty" error message

    @test @login @dataTableTest
    Scenario: User Fails Login
      When user enters combinations of valid and invalid or blank username and password and clicks login button and verify the error message
      |username|password|expectedMessage|
      |valid   |invalid |Invalid credentials|
      |invalid |valid   |Invalid credentials|
      |        |valid   |Username cannot be empty|
      |valid   |        |Password cannot be empty|
      Then verify the login  error message
