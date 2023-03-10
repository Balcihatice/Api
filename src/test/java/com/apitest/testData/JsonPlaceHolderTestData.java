package com.apitest.testData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {


    public Map<String, Object> setUpTestData() {

        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("statusCode", 200);
        expectedData.put("via", "1.1 vegur");
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("userId", 1);
        expectedData.put("Server", "cloudflare");
        expectedData.put("completed", false);
        return expectedData;
    }

    /*
    https://jsonplaceholder.typicode.com/todos urlsine asagidaki body gonderildiginde
    {
    "userId": 55;
    "title": "Tidy your room",
    "completed": false
    }
    Donen responsun statuscodeun 201 ve response nin sagidaki gibi oldugunu test edin
     {
    "userId": 55;
    "title": "Tidy your room",
    "completed": false
    }
     */
    public JSONObject setUpPostData() {

        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("statusCode",201);
        expectedRequest.put("userId", 55);
        expectedRequest.put("title", "Tidy your room");
        expectedRequest.put("completed", false);
        return expectedRequest;

    }
/*Put Request
    {
        "userId": 21,
            "title": "Wash the dishes",
            "completed": false
    }
 */

    public  JSONObject setUpPutData() {
        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("userId", 21);
        expectedRequest.put("title", "Wash the dishes");
        expectedRequest.put("completed", false);
        return expectedRequest;
    }


    /*Patch Request
    {
        "title": "API calismaliyim",
    }
*/
    public  JSONObject setUpPatcRequestData() {
        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("title", "API calismaliyim");
        return expectedRequest;
    }

 /*   {
        "userId": 10;
        "title": "API calismaliyim",
            "completed": true,
            "id": 198
    }*/
    public JSONObject setUpPatchExpectedData(){
        JSONObject expectedData = new JSONObject();
        expectedData.put("userId", 10);
        expectedData.put("title", "API calismaliyim");
        expectedData.put("copmleted", true);
        expectedData.put("id", 198);
        return expectedData;
    }
}