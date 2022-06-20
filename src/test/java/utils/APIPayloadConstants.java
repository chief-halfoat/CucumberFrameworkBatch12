package utils;

public class APIPayloadConstants {
    public static String createEmployeePayload(){
        String createEmployee = "{\n" +
                "  \"emp_firstname\": \"Wanda\",\n" +
                "  \"emp_lastname\": \"Maximoff\",\n" +
                "  \"emp_middle_name\": \"W\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1987-06-11\",\n" +
                "  \"emp_status\": \"Probation\",\n" +
                "  \"emp_job_title\": \"Avenger\"\n" +
                "}";


        return createEmployee;
    }
}
