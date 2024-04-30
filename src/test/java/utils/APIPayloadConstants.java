package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createEmployeePayload() {
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"-----\",\n" +
                "  \"emp_lastname\": \"-----\",\n" +
                "  \"emp_middle_name\": \"-----\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"-----\",\n" +
                "  \"emp_status\": \"-----\",\n" +
                "  \"emp_job_title\": \"-----\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeeJsonPayload() {

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "-----");
        obj.put("emp_lastname", "-----");
        obj.put("emp_middle_name", "-----");
        obj.put("emp_gender", "-----");
        obj.put("emp_birthday", "-----");
        obj.put("emp_status", "-----");
        obj.put("emp_job_title", "-----");
        return obj.toString();

    }

    public static String createEmployeeJsonPayloadDynamic
            (String fn, String ln, String mn, String gender,
             String dob, String status, String jobTitle) {
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", fn);
        obj.put("emp_lastname", ln);
        obj.put("emp_middle_name", mn);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", dob);
        obj.put("emp_status", status);
        obj.put("emp_job_title", jobTitle);
        return obj.toString();

    }

    public static String updatedEmployeePayload(String empID) {
        String updatedEmplPayload = "{\n" +
                "  \"employee_id\": \""+empID+"\",\n" +
                "  \"emp_firstname\": \"-----\",\n" +
                "  \"emp_lastname\": \"-----\",\n" +
                "  \"emp_middle_name\": \"-----\",\n" +
                "  \"emp_gender\": \"-----\",\n" +
                "  \"emp_birthday\": \"-----\",\n" +
                "  \"emp_status\": \"-----\",\n" +
                "  \"emp_job_title\": \"-----\"\n" +
                "}";
        return updatedEmplPayload;
    }

    public static String updateEmployeeJsonPayload(String empID) {

        JSONObject obj = new JSONObject();
        obj.put("employee_id", empID);
        obj.put("emp_firstname", "-----");
        obj.put("emp_lastname", "-----");
        obj.put("emp_middle_name", "-----");
        obj.put("emp_gender", "-----");
        obj.put("emp_birthday", "-----");
        obj.put("emp_status", "-----");
        obj.put("emp_job_title", "uknowed");
        return obj.toString();

    }
    public static String updateEmployeeJsonPayloadDynamic(String empId, String fn, String mn, String ln, String eb, String gender, String jT, String status){
        JSONObject obj= new JSONObject();
        obj.put("employee_id", empId);
        obj.put("emp_firstname", fn);
        obj.put("emp_middle_name", mn);
        obj.put("emp_lastname", ln);
        obj.put("emp_birthday", eb);
        obj.put("emp_gender", gender);
        obj.put("emp_job_title", jT);
        obj.put("emp_status", status);
        return obj.toString();
    }

    public static String partialUpdateEmployee (String empID){
        String partialUpdateEmplPayload="{\n" +
                "  \"employee_id\": \""+empID+"\",\n" +
                "  \"emp_firstname\": \"-----\"\n" +
                "}";
        return partialUpdateEmplPayload;
    }

    public static String partialUpdateEmplDynamic(String empID, String ln){

        JSONObject obj=new JSONObject();
        obj.put("employee_id", empID);
        obj.put("emp_lastname", ln);
        return obj.toString();
    }

}

