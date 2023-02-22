package com.apitest.day11;

import com.apitest.testBase.JsonPlaceHolderTestBase;
import com.apitest.testData.JsonPlaceHolderTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostRequest03 extends JsonPlaceHolderTestBase {

    /*
    https://jsonplaceholder.typicode.com/todos urlsine asagidaki body gonderildiginde
   https://jsonplaceholder.typicode.com/todos
    Donen responsun statuscodeun 201 ve response nin sagidaki gibi oldugunu test edin
     {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
    }
     */

    @Test
    public void test() {
        spec01.pathParam("parametre1", "todos");

        JsonPlaceHolderTestData testObjesi = new JsonPlaceHolderTestData();
        JSONObject expectedRequest = testObjesi.setUpPostData();
        System.out.println(expectedRequest);

        Response response = given().
                accept("application/json").
                spec(spec01).
                body(expectedRequest.toString()).
                when().
                post("/{parametre1}");
        response.prettyPrint();

       // Matchers class
        response.then().assertThat()
                .statusCode(expectedRequest.getInt("statusCode"))
                .body("completed", equalTo(expectedRequest.getBoolean("completed")),
                        "title", equalTo(expectedRequest.getString("title")),
                        "userId", equalTo(expectedRequest.getInt("userId")));


        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedRequest.getInt("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedRequest.getString("title"), json.getString("title"));
        Assert.assertEquals(expectedRequest.getInt("userId"), json.getInt("userId"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"), json.getString("completed"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"), json.getBoolean("completed"));


        //de serialization

        HashMap<String,Object> actualDataMap = response.as(HashMap.class);
        Assert.assertEquals(expectedRequest.getString("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedRequest.getInt("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"), actualDataMap.get("completed"));
    }
}
