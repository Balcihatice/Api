package com.apitest.day11;

import com.apitest.testBase.JsonPlaceHolderTestBase;
import com.apitest.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PutRequest01 extends JsonPlaceHolderTestBase {

     /*Put request
    https://jsonplaceholder.typicode.com/todos198 urlsine asagidaki body gonderildiginde
    {
    "userId": 21,
    "title": "Wash the dishes",
    "completed": false
    }
    Donen responsun statuscodeun 200 ve response nin sagidaki gibi oldugunu test edin
     {
    "userId": 21;
    "title": "Wash the dishes",
    "completed": false,
    "id": 198
    }
     */

    @Test
    public void test() {
        spec01.pathParams("parametre1", "todos", "parametre2", "198");

        JsonPlaceHolderTestData testObje = new JsonPlaceHolderTestData();
        JSONObject expectedRequest = testObje.setUpPutData();
        System.out.println(expectedRequest);

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin", "password").
                body(expectedRequest.toString()).
                when().
                put("/{parametre1}/{parametre2}");
        response.prettyPrint();

        //JsonPath
        JsonPath json = response.jsonPath();
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedRequest.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"),json.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),json.getBoolean("completed"));


    }
}
