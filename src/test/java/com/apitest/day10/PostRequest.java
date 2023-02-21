package com.apitest.day10;

import com.apitest.testBase.DummyTestBase;
import com.apitest.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest extends DummyTestBase {
 /* https://dummy.restapiexample.com/api/v1/create urlsine  Request  body olarak
 {
        "name":"Ahmet Aksoy",
            "Salary": "1000",
            "age": "18",
            "profile_image": ""

    }gonderildiginde StstusCodeun 200 oldugunu ve response body nin,
    {
    "status":"success,
    "data}:{
    "id"...
    },
    "message"."Succesfully! Record has been added."
   oldugunu test edin
  */


    @Test
    public void test() {
        spec03.pathParam("parametre1", "create");

        DummyTestData obje = new DummyTestData();

        //Postrequest yaparken biz body gondermek zorundayiz, testdata classinda olusturdugumuz
        //request body yi burada cagiriyoruz.
        HashMap<String, String> requestBodyMap = obje.setUpRequestBody();//request gonderecegimiz
        HashMap<String, Object> expectedDataMap = obje.setUpExpectedData();//rsponse dan donen
        Response response = given().
                accept("aapplication/json").
                spec(spec03).auth().basic("admin", "password123").
                when().post("/{parametre1}");
        response.prettyPrint();

        //De Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));

        //JsonPath
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("status"), jsonPath.getString("status"));
        Assert.assertEquals(expectedDataMap.get("message"), jsonPath.getString("message"));

    }
}
