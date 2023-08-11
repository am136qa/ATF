Feature: Syntax API HRMS Flow

  Background:
    Given a JWT is generated

  @api  @employeeUpdate @pu
  Scenario: Creating the employee using API
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable

  @api
  Scenario: Get the created employee
    Given a request is prepared for retrieving an employee
    When a GET call is made to retrieve the employee
    Then the status code for this employee is 200
    And the employee id "employee.employee_id" must match with globaly stored employee id
    And this employee data at "employee" object matches with the dta used to create the employee
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title |
      | Jonny         | Silver       | powerhead       | Male       | 1983-07-23   | Happy      | QA            |

  @json
  Scenario: Creating an employee using json body
    Given a request is prepared for creating an employee using json payload
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable

  @dynamic
  Scenario: Creating an employee using highly dynamic scenario
    Given a request is prepared for creating an employee with "Jonny", "Silver", "powerhead", "M", "1983-07-23", "happy", "QA"
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable

  @employeeUpdate
  Scenario: Updating the employee using API
    Given a request is prepared for updating the employee
    When a PUT call is made to update an employee
    Then the status code for updating an employee is 200
    And emplyee updated contains key "Message" and value "Employee record Updated"

  @employeeUpdate
  Scenario: Get the updated employee
    Given a request is prepared for retrieving an employee
    When a GET call is made to retrieve the employee
    Then the status code for this employee is 200
    And this employee data at "employee" object matches with the dta used to create the employee
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title |
      | Jack          | Sparrow      | Pirate          | Male       | 2023-07-29   | pathetic   | uknowed       |

  @employeeUpdate
  Scenario: Updating the employee using JSON
    Given a request is prepared for updating the employee using json payload
    When a PUT call is made to update an employee
    Then the status code for updating an employee is 200
    And emplyee updated contains key "Message" and value "Employee record Updated"

  @employeeUpdate
  Scenario: Updating the employee using highly dynamic scenario
    Given a request is prepared for updating employee with data "employee_id","Jo","SL","Silver","2013-07-29","M","QA","pathetic"
    When a PUT call is made to update an employee
    Then the status code for updating an employee is 200
    And emplyee updated contains key "Message" and value "Employee record Updated"

  @pu
  Scenario: Partial updating the employee
    Given a request is prepared for partial updating the employee
    When PATCH call is made to partial update an employee
    Then the status code for creating an employee is 201
    And employee updated contains key "Message" and value "Employee record updated successfully"

  @pu
  Scenario: Partial updating the employee using highly dynamic scenario
    Given a request is prepared for partial updating the employee with new data lastname "Gold"
    When PATCH call is made to partial update an employee
    Then the status code for creating an employee is 201
    And employee updated contains key "Message" and value "Employee record updated successfully"
    And the employee id "Employee.employee_id" is stored as a global variable
