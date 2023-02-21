package com.apitest.day04;


import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest03 {

    //https://restful-booker.herokuapp.com/booking/1001  url ne
    //accept type`i "application/json" olan GET reueat yolladigimda gelen response`un
    //status codeun 404 ve
    //Respons bodysinin "Not Found" icerdigini ve
    //Respons bodysinin "API" icermedigini test edin
    @Test
    public void test() {
        String url = "https://restful-booker.herokuapp.com/booking/1001";
        Response response = given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();
    //response.asString()->>response`u stringe cevirir.(contains ile kullanmak istersek)
        response.then().
                assertThat().
                statusCode(404);

        Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertFalse(response.asString().contains("API"));


    }
}