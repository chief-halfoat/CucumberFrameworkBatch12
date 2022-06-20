package utils;

import org.json.JSONObject;

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
    //passing the body from JSON object
    public static String createEmployeePayloadViaJSON(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname","Herbert");
        obj.put("emp_lastname","Jackson");
        obj.put("emp_middle_name","Youngblood");
        obj.put("emp_gender","M");
        obj.put("emp_birthday","1950-01-26");
        obj.put("emp_status","Retired");
        obj.put("emp_job_title","Defensive End");
        return obj.toString();
    }

    //passing the body from the feature file dynamically
    public static String createEmployeeDynamic(
            String firstName, String lastName, String middleName, String gender, String dob, String jobStatus, String jobTitle
    ){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname",firstName);
        obj.put("emp_lastname",lastName);
        obj.put("emp_middle_name",middleName);
        obj.put("emp_gender",gender);
        obj.put("emp_birthday",dob);
        obj.put("emp_status",jobStatus);
        obj.put("emp_job_title",jobTitle);
        return obj.toString();
    }
}
