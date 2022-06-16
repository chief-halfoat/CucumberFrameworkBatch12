package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTUzMzQ5NzgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NTM3ODE3OCwidXNlcklkIjoiMzc3NyJ9.6vZIRiTVX657Iz8tyiMqbPu0n7wzuS5DDR4c5MWxwsE";
    static String employee_id;
    @Test
    public void acreateEmployee(){
       RequestSpecification request =  given().header("Content-Type","application/json").header("Authorization",token).body("{\n" +
                "  \"emp_firstname\": \"Wanda\",\n" +
                "  \"emp_lastname\": \"Maximoff\",\n" +
                "  \"emp_middle_name\": \"W\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1987-06-11\",\n" +
                "  \"emp_status\": \"Probation\",\n" +
                "  \"emp_job_title\": \"Avenger\"\n" +
                "}");

      Response response =  request.when().post("/createEmployee.php");

      response.prettyPrint();
      response.then().assertThat().statusCode(201);

      //Hamcrest Matchers
        response.then().assertThat().body("Message",equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname",equalTo("Wanda"));
        employee_id=response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }
    @Test
    public void bgetCreatedEmployee(){
        RequestSpecification request = given().header("Content-Type","application/json").header("Authorization",token).queryParam("employee_id",employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        String tempID = response.jsonPath().getString("employee.employee_id");
        System.out.println(tempID);
        Assert.assertEquals(tempID,employee_id);
    }
//    @Test
//    public void cupdateEmployee(){
//        RequestSpecification request = given().header("Content-Type","application/json").header("Authorization",token).body(
//                "{\n" +
//                "  \"employee_id\": \" "+ employee_id + " \",\n" +
//                        "  \"emp_firstname\": \"edward\",\n" +
//                        "  \"emp_lastname\": \"sisi\",\n" +
//                        "  \"emp_middle_name\": \"MS1\",\n" +
//                        "  \"emp_gender\": \"M\",\n" +
//                        "  \"emp_birthday\": \"1995-06-12\",\n" +
//                        "  \"emp_status\": \"confirmed\",\n" +
//                        "  \"emp_job_title\": \"Manager\"\n" +
//                        "}");
//
//        Response response = request.when().put("/updateEmployee.php");
//        response.prettyPrint();
//        response.then().assertThat().statusCode(200);
//    }
@Test
public void cupdateEmployee(){
    RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
            header("Authorization", token).
            body("{\n" +
                    "        \"employee_id\": \""+ employee_id + "\",\n" +
                    "        \"emp_firstname\": \"edward\",\n" +
                    "        \"emp_lastname\": \"sisi\",\n" +
                    "        \"emp_middle_name\": \"MS1\", \n" +
                    "        \"emp_gender\": \"M\",       \n" +
                    "        \"emp_birthday\": \"1995-06-12\", \n" +
                    "        \"emp_status\": \"confirmed\",       \n" +
                    "        \"emp_job_title\": \"Manager\"        \n" +
                    "    }");

    Response response =preparedRequest.when().put("/updateEmployee.php");
    response.prettyPrint();
    response.then().assertThat().statusCode(200);
}

    @Test
    public void dgetupdatedEmployee (){
        RequestSpecification request = given().header("Content-Type","application/jason").header("Authorization",token).queryParam("employee_id",employee_id);

        Response response = request.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void eGetAllEmployees(){
        RequestSpecification request = given().header("Authorization",token).header("Content-Type","application/json");
        Response response = request.when().get("getAllEmployees.php");
        String allEmployees = response.prettyPrint();
        JsonPath js = new JsonPath(allEmployees);
        int count = js.getInt("Employees.size()");
        System.out.println(count);

        for (int i = 0; i < count; i++) {
            String empId = js.getString("Employees["+i+"].employee_id");
            System.out.println(empId);
        }
    }
}
