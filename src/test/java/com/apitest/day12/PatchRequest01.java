package com.apitest.day12;

import com.apitest.testBase.JsonPlaceHolderTestBase;
import com.apitest.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends JsonPlaceHolderTestBase {
    /*Patch request
    https://jsonplaceholder.typicode.com/todos198 urlsine asagidaki body gonderildiginde
    {

    "title": "API calismaliyim",

    }
    Donen responsun statuscodeun 200 ve response nin sagidaki gibi oldugunu test edin
     {
    "userId": 10;
    "title": "API calismaliyim",
    "completed": true,
    "id": 198
    }
     */

    @Test
    public void test() {
        //url
        spec01.pathParams("parametre1", "todos", "parametre2", "198");

        //expected ve request olusturmak
        JsonPlaceHolderTestData testObje = new JsonPlaceHolderTestData();
        JSONObject requestData = testObje.setUpPatcRequestData();
        JSONObject expectedData = testObje.setUpPatchExpectedData();
        System.out.println(requestData);
        System.out.println(expectedData);

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin", "password123").
                body(requestData.toString()).
                when().
                patch("/{parametre1}/{parametre2}");
        response.prettyPrint();

        //json
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedData.getString("title"), json.getString("title"));

        //de serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.getInt("userId"),actualData.get("usreId"));
        Assert.assertEquals(expectedData.getInt("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.getString("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.getBoolean("completed"),actualData.get("completed"));
    }
}