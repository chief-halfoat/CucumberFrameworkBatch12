Feature: Adding the employees in HRMS Application

  @regression
  Scenario: Adding one employee from feature file
    Given user is navigated to HRMS application
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user successfully logged in
    When user clicks on pim option
    And user clicks on add employee option
    And user enters firstName, middleName, and lastName
    And user clicks on save button
    Then employee is added successfully
