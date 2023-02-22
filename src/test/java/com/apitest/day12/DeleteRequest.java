package com.apitest.day12;

import com.apitest.testBase.DummyTestBase;
import com.apitest.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeleteRequest extends DummyTestBase {

    /*
    https://dummy.restapiexample.com/api/v1/delete/2 bir delete request gonderildiginde
    donen responsun status code 200 ve body kisminin asagidaki gibi oldugunu test edin
    {
    "status": "success,
    "data": "2"
    "message": "Successfully! Record has been deleted"
    }
     */

    @Test
    public void test() {
        spec03.pathParams("parametre1", "delete", "parametre2", "2");

        DummyTestData testData = new DummyTestData();
        JSONObject expectedData = testData.setUpDeleteExpectedData();

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec03).
                auth().
                basic("admin", "password123").
                when().
                delete("/{parametre1}/{parametre2}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).
                body("status", equalTo(expectedData.getString("status")),
                        "message", equalTo(expectedData.getString("message")),
                        "data", equalTo(expectedData.getString("data")));
    }
}