package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matcher.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    //baseURI=baseURL+endpoint
    //given-preparation
    //when - hitting the endpoint
    //base URI=base URL
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTA5OTEwNDEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5MTAzNDI0MSwidXNlcklkIjoiNTYxOSJ9.K-1XBzmhVMVPP4r-4-cKRn7qnHEVcGg24G5c3jCpWtQ";
    static String employee_id;

    //in this method we are going to create employee
    //we need headers, body to prepare request
    @Test
    public void acreateEmployee() {
        //prepare the request
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"Jonny\",\n" +
                        "  \"emp_lastname\": \"Silver\",\n" +
                        "  \"emp_middle_name\": \"powerhead\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1983-07-23\",\n" +
                        "  \"emp_status\": \"happy\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");
        //hitting the endpoint
        Response response = request.when().post("/createEmployee.php");
        //verifying the response
        response.then().assertThat().statusCode(201);
        //  System.out.println(response);
        //this method be used to print the response of API in console
        response.prettyPrint();
        //verify all the values and headers from response
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Jonny"));
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("powerhead"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().header("X-Powered-By", "PHP/7.2.18");
        //it will returne the employee id and saved it in variable
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

    }

    @Test
    public void bgetCreatedEmployee() {
        RequestSpecification request = given().header("Authorization", token).
                queryParam("employee_id", employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

        String tempEmpId = response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id,tempEmpId);

    }
    @Test
    public void cUpdateEmployee(){

        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"Jack\",\n" +
                        "  \"emp_lastname\": \"Parrow\",\n" +
                        "  \"emp_middle_name\": \"Pirate\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2023-07-29\",\n" +
                        "  \"emp_status\": \"pathetic\",\n" +
                        "  \"emp_job_title\": \"uknowed\"\n" +
                        "}");

        Response response=request.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
        response.prettyPrint();

    }
    @Test
    public void dgetCreatedEmployee() {
        RequestSpecification request = given().header("Authorization", token).
                queryParam("employee_id", employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

        String tempEmpId = response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id,tempEmpId);
    }

    @Test
    public void fpartialUpdateEmployee(){
        RequestSpecification request = given().header("Content-Type","application/json").
                header("Authorization",token).body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_lastname\": \"Sparrow\"\n" +
                        "}");
        Response response=request.when().patch("/updatePartialEmplyeesDetails.php");
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Message",equalTo("Employee record updated successfully"));
        response.prettyPrint();
    }
    @Test
    public void ggetPartialUpdatedEmployee(){
        //dgetCreatedEmployee();

       RequestSpecification request = given().header("Authorization",token).
                queryParam("employee_id", employee_id);
        Response response=request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

        String tempEmpId=response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id,tempEmpId);
    }

}
