Feature: US-12345 - Search an employee in HRMS
  Scenario: Search an employee by ID
    * user is navigated to HRMS application
    * user enters valid admin credentials
    * user clicks on login button
    * user navigated to employee list page
    * user enters valid employee id
    * user clicks on search button
    * user is able to see employee information

  Scenario: Search an employee by name
    * user is navigated to HRMS application
    * user enters valid admin credentials
    * user clicks on login button
    * user navigated to employee list page
    * user enters valid employee name
    * user clicks on search button
    * user is able to see employee information
