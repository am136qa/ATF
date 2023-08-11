Feature: Adding new jobs in HRMS

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    And user is successfully logged in the application

  @addJob
  Scenario: User add a new job
    * user click on the admin button
    * user click on the job
    * user click on the Job Title
    * user clicks on the add button
    * user enters job "Java and Python Instructor" title
    * user enters job description "teaches java and python"
    * user enters job note "Java and python programming note"
    * user clicks on the save button
    * verify data is stored properly in database