package com.apitest.day07;

import com.apitest.testBase.JsonPlaceHolderTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest11 extends JsonPlaceHolderTestBase {

    /*
    https://jsonplaceholder.typicode.com/todos/2 url sine istek gonderildiginde
    Donen respons un
    "completed"degerinin false
    "title" degerinin "quis ut nam facilis et officia qui"
    "userId" sinin 1 ve header degerinden
    "via"derinin 1.1 vegur" ve
    "Server" degerinin "cloudflare" oldugunu test edin
     */

        /*
        url olustur
        expected data
        request gonder
        qctual data
        assertion
        */

    @Test
    public void test() {
        spec01.pathParams("parametre1", "todos",
                "parametre2", 2);

        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("statusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("userId", 1);
        expectedData.put("Server", "cloudflare");
        System.out.println(expectedData);
        System.out.println("====================================");
        Response response = given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");
        response.prettyPrint();


        //1.Yontem Matchers class ile assertion yaptik
  //    response.then().
  //            assertThat().
  //            statusCode((Integer) expectedData.get("statusCode")).
  //            headers("Via", equalTo(expectedData.get("Via")),
  //                    "Server", equalTo(expectedData.get("Server"))).
  //            body("userId", equalTo(expectedData.get("userId")),
  //                    "title", equalTo(expectedData.get("title")),
  //                    "completed", equalTo(expectedData.get("completed")));
  //

        //2.Yontem
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedData.get("statusCode"), response.statusCode());
        Assert.assertEquals(expectedData.get("Server"), response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("Via"), response.getHeader("Via"));
        Assert.assertEquals(expectedData.get("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));



        //3.yontem deserialization
        // --object mapper
        // --pojo class ile birlikte map kullanacagiz



    }
}
