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
}