Feature: Adding the employees in HRMS Application

  Background:
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user successfully logged in
    When user clicks on pim option
    And user clicks on add employee option

  @regression
  Scenario: Adding one employee from feature file
    #Given user is navigated to HRMS application
    And user enters firstName, middleName, and lastName
    And user clicks on save button
    Then employee is added successfully

  Scenario: Adding one employee using Cucumber feature file
    And user enters "Harry" "S" "Truman"
    And user clicks on save button
    Then employee is added successfully

  Scenario Outline: Adding multiple employees
    And user provides "<firstName>" "<middleName>" and "<lastName>"
    And user clicks on save button
    Then employee is added successfully
    Examples:
      | firstName | middleName | lastName  |
      | Hunter    | S          | Thompson  |
      | Fritz     | the        | Cat       |
      | Steven    | Harold     | Gutenberg |
      | Laura     | Jane       | Grace     |

  @datatable
  Scenario: Add employee using Data Table
    When user provides multiple employee's data and verify they are added
      | firstName | middleName | lastName  |
      | Hunter    | S          | Thompson  |
      | Fritz     | the        | Cat       |
      | Steven    | Harold     | Gutenberg |
      | Laura     | Jane       | Grace     |

