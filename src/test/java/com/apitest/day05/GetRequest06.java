package com.apitest.day05;

import com.apitest.testBase.JsonPlaceHolderTestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest06 extends JsonPlaceHolderTestBase {
    //https://jsonplaceholder.typicode.com/todos/123 adresine bir request gonderildiginde
    //accept type`i "application/json" olan GET reueat yolladigimda gelen response`un
    //status codeun 200
    //content-type "application/json" oldugunu test edin
    //headers daki "Server" in "cloudflare"
    // ve title"in "esse et quis iste est earum aut impedit"
    // ve "completed" bolumunun false oldugunu test edin

    @Test
    public void test01() {
        // String url = "https://jsonplaceholder.typicode.com/todos/123";
        spec01.pathParams("parametre1", "todos", "parametre2", 123);

        Response response = given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                header("Server", equalTo("cloudflare")).
                body("userId", equalTo(7),
                "title", equalTo("esse et quis iste est earum aut impedit"),
                "completed", equalTo(false));
    }
}