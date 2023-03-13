package com.apitest.Calismam;

import com.apitest.testBase.JsonPlaceHolderTestBase;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class Get05 extends JsonPlaceHolderTestBase {
    @Test
    public void test01() {

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
                header("Server","cloudflare").
                body("title",equalTo("esse et quis iste est earum aut impedit"),
                        "completed",equalTo(false));

    }
}
