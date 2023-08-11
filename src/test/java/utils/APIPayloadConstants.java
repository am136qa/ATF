package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createEmployeePayload() {
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Jonny\",\n" +
                "  \"emp_lastname\": \"Silver\",\n" +
                "  \"emp_middle_name\": \"powerhead\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1983-07-23\",\n" +
                "  \"emp_status\": \"happy\",\n" +
                "  \"emp_job_title\": \"QA\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeeJsonPayload() {

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Jonny");
        obj.put("emp_lastname", "Silver");
        obj.put("emp_middle_name", "powerhead");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "1983-07-23");
        obj.put("emp_status", "happy");
        obj.put("emp_job_title", "QA");
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
                "  \"emp_firstname\": \"Jack\",\n" +
                "  \"emp_lastname\": \"Sparrow\",\n" +
                "  \"emp_middle_name\": \"Pirate\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2023-07-29\",\n" +
                "  \"emp_status\": \"pathetic\",\n" +
                "  \"emp_job_title\": \"uknowed\"\n" +
                "}";
        return updatedEmplPayload;
    }

    public static String updateEmployeeJsonPayload(String empID) {

        JSONObject obj = new JSONObject();
        obj.put("employee_id", empID);
        obj.put("emp_firstname", "Jonny");
        obj.put("emp_lastname", "Silver");
        obj.put("emp_middle_name", "Pirate");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2023-07-29");
        obj.put("emp_status", "pathetic");
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
                "  \"emp_firstname\": \"Jack\"\n" +
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

