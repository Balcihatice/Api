package com.apitest.day08;


import com.apitest.testBase.HerokuAppTestBase;
import com.apitest.testData.HerokuAppTestData;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends HerokuAppTestBase {

    /*https://restful-booker.herokuapp.com/booking/1 adresine bir request gonderildiginde
    donecek cevap(response) icin
    "firstname" "Sally"
    "lastname":"Smith"
    "totalprice":667
    "depositpaid":true,
    "bookingdates":{
    "checkin":"2018-07-27"
    "checkout":"2019-10-20"
    }
    gibi oldugunu test edin

    */

    @Test
    public void test() {

        spec02.pathParams("parametre1", "booking", "parametre2", 2);

        HerokuAppTestData expectedObje = new HerokuAppTestData();
        HashMap<String, Object> expectedDataMap = expectedObje.setUpTestData();
        System.out.println(expectedDataMap);


        Response response = given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        Assert.assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));

        Assert.assertEquals( ((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualDataMap.get("bookingdates")).get("checkin"));

        Assert.assertEquals( ((Map)expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map)actualDataMap.get("bookingdates")).get("checkout"));
    }
}
