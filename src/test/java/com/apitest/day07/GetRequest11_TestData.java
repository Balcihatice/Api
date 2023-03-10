package com.apitest.day07;

import com.apitest.testBase.JsonPlaceHolderTestBase;
import com.apitest.testData.JsonPlaceHolderTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class GetRequest11_TestData extends JsonPlaceHolderTestBase {

    @Test
    public void test() {
        spec01.pathParams("parametre1", "todos",
                "parametre2", 2);

        JsonPlaceHolderTestData expectedObje = new JsonPlaceHolderTestData();
        HashMap<String, Object> expectedData = (HashMap<String, Object>) expectedObje.setUpTestData();
        System.out.println(expectedData);


        System.out.println("====================================");
        Response response = given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");
        response.prettyPrint();


        //1.Yontem Matchers class ile assertion yaptik
            response.then().
                    assertThat().
                    statusCode((Integer) expectedData.get("statusCode")).
                    headers("via", equalTo(expectedData.get("via")),
                            "Server", equalTo(expectedData.get("Server"))).
                    body("userId", equalTo(expectedData.get("userId")),
                            "title", equalTo(expectedData.get("title")),
                            "completed", equalTo(expectedData.get("completed")));


        //2.Yontem
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedData.get("statusCode"), response.statusCode());
        Assert.assertEquals(expectedData.get("Server"), response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("via"), response.getHeader("via"));
        Assert.assertEquals(expectedData.get("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));


        //3.yontem deserialization
        // --object mapper
        // --pojo class ile birlikte map kullanacagiz
        //Headerdaki bilgileri map`e atamiyoruz,sadece body aliyoruz
        HashMap<String, Object> actualData = response.as(HashMap.class);//responseden geleni yaniti actual dataya  ata

        System.out.println(actualData);

        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }

}
