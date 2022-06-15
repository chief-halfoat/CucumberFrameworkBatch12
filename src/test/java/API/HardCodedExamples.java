package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTUyNDg1MzUsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NTI5MTczNSwidXNlcklkIjoiMzc3NyJ9.8K5gXYyWBynYBvQso-2H_lGIboeJed803u1OyKZkP0I";
    static String employee_id;
    @Test
    public void createEmployee(){
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
}
