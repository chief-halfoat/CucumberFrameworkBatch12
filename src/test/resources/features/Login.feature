Feature: Validation of login credentials

  Background:
  #  Given user is navigated to HRMS application

  @regression @batch12 @smoke @sprint12
  Scenario: Admin Login
    #Given user is navigated to HRMS application
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user successfully logged in

    @regression
  Scenario: ESS Login
    #Given user is navigated to HRMS application
    When user enters valid ess username and password
    And user clicks on login button
    Then ess user is successfully logged in
  @regression
  Scenario: Invalid Login
    #Given user is navigated to HRMS application
    When users enters invalid username and password
    And user clicks on login button
    Then user sees error message on the screen