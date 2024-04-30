package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.DBUtils;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    String fnFirstName;
    String fnMiddleName;
    String fnLastName;
    String empId;

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
       
        click(dashboardPage.pimOption);
    }

    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
       
        click(dashboardPage.addEmployeeButton);
    }

    @When("user enters firstname and lastname")
    public void user_enters_firstname_and_lastname() {
    
        sendText("aendro", addEmployeePage.firstNameField);

        sendText("farewell", addEmployeePage.lastNameField);
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {

        click(addEmployeePage.saveButton);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");
    }

    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String firstName, String middleName, String lastName) {
        this.fnFirstName=firstName;
        this.fnMiddleName=middleName;
        this.fnLastName=lastName;
        sendText(firstName, addEmployeePage.firstNameField);
        sendText(middleName, addEmployeePage.middleNameField);
        sendText(lastName, addEmployeePage.lastNameField);
        empId=addEmployeePage.employeeIdField.getAttribute("value");

    }


    @When("user enters {string} and {string} and {string} in data driven format")
    public void user_enters_and_and_in_data_driven_format(String firstName, String middleName, String lastName) {
        sendText(firstName, addEmployeePage.firstNameField);
        sendText(middleName, addEmployeePage.middleNameField);
        sendText(lastName, addEmployeePage.lastNameField);
    }

    @When("user enters firstname and middlename and lastname and verify employee has added")
    public void user_enters_firstname_and_middlename_and_lastname_and_verify_employee_has_added
            (io.cucumber.datatable.DataTable dataTable) {
       
        List<Map<String, String>> employeeNames = dataTable.asMaps();

        for (Map<String, String> employee : employeeNames
        ) {
  
            String firstNameValue = employee.get("firstName");
            String middleNameValue = employee.get("middleName");
            String lastNameValue = employee.get("lastName");


            sendText(firstNameValue, addEmployeePage.firstNameField);
            sendText(middleNameValue, addEmployeePage.middleNameField);
            sendText(lastNameValue, addEmployeePage.lastNameField);
            click(addEmployeePage.saveButton);
            click(dashboardPage.addEmployeeButton);
        }
    }

    @When("user adds multiple employees using excel from {string} and verify it")
    public void user_adds_multiple_employees_using_excel_from_and_verify_it
            (String sheetName) throws InterruptedException {
   
        List<Map<String, String>> newEmployees =
                ExcelReader.read(sheetName, Constants.EXCEL_READER_PATH);

        Iterator<Map<String, String>> itr = newEmployees.iterator();

        while (itr.hasNext()) {

       
            Map<String, String> mapNewEmp = itr.next();

            sendText(mapNewEmp.get("firstName"), addEmployeePage.firstNameField);
            sendText(mapNewEmp.get("lastName"), addEmployeePage.lastNameField);
            sendText(mapNewEmp.get("middleName"), addEmployeePage.middleNameField);
            sendText(mapNewEmp.get("photograph"), addEmployeePage.photograph);

            //we can enter username and password only after selecting the checkbox
            if (!addEmployeePage.checkBoxLocator.isSelected()) {
                click(addEmployeePage.checkBoxLocator);
            }
            sendText(mapNewEmp.get("username"), addEmployeePage.usernameTextFieldBox);
            sendText(mapNewEmp.get("password"), addEmployeePage.passwordTextFieldBox);
            sendText(mapNewEmp.get("confirmPassword"), addEmployeePage.confirmPasswordBox);

   
            String empIdValue = addEmployeePage.employeeIdField.getAttribute("value");
            Assert.assertTrue(addEmployeePage.saveButton.isDisplayed());
            click(addEmployeePage.saveButton);
            Thread.sleep(3000);

            click(dashboardPage.empListOption);
 
            sendText(empIdValue, employeeSearchPage.idTextField);
            click(employeeSearchPage.searchButton);


            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for (int i = 0; i < rowData.size(); i++) {
                System.out.println("I am inside the loop");
         
                String rowText = rowData.get(i).getText();
         
                System.out.println(rowText);
   

                String expectedData = empIdValue + " " + mapNewEmp.get("firstName") + " " +
                        mapNewEmp.get("middleName") + " " + mapNewEmp.get("lastName");

                Assert.assertEquals(expectedData, rowText);
               
            }
            click(dashboardPage.addEmployeeButton);
        }

    }


    @Then("verify employee is stored in database")
    public void verifyEmployeeIsStoredInDatabase() {

        String query="select emp_firstName,emp_middle_name,emp_lastname from hs_hr_employees where employee_id=" + empId + ";";
        System.out.println(query);
        List<Map<String,String>> mapList=DBUtils.fetch(query);
        Map<String,String> firstRow=mapList.get(0);
        String dbFirstName=firstRow.get("emp_firstName");
        String dbMiddleName=firstRow.get("emp_middle_name");
        String dbLastName=firstRow.get("emp_lastname");

        Assert.assertEquals("FirstName From frontend does not match the firstname from database",fnFirstName,dbFirstName);
        Assert.assertEquals("MiddleName From frontend does not match the Middlename from database",fnMiddleName,dbMiddleName);
        Assert.assertEquals("LastName From frontend does not match the lasttname from database",fnLastName,dbLastName);


    }
}
