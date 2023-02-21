package com.apitest.day10;


import com.apitest.testBase.HerokuAppTestBase;
import com.apitest.testData.HerokuAppTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerokuAppTestBase {
 /*
       JsonObjec post yaparken java kullanmak yerine kullanabiliriz.
       JsonObject class indan bir obje olusturarak kullaniriz.
       JsonObjectlerde type belirtmedigimiz icin type casting islemi yapmayiz.
       JsonObject kullaanabilmek icin  kutuphane eklemek gerekir
       Request gonderilirken body icerisinde toString methodu kullanilmasi gerekir
  */

/*
https://www.resfulful-booker.herokuapp.com/booking url ine asagidaki post
 "firstname" "Sally"
             "lastname":"Smith"
             "totalprice":667
             "depositpaid":true,
             "bookingdates": {
        "checkin":"2018-07-27"
        "checkout":"2019-10-20" gonderildigin de Donen responsun


"booking":{"firstname" "Sally"
             "lastname":"Smith"
             "totalprice":667
             "depositpaid":true,
             "bookingdates": {
        "checkin":"2018-07-27"
        "checkout":"2019-10-20"
        oldugunu ddogrulayin
      }
    }
 */

    @Test
    public void test() {
        //url
        spec02.pathParam("parametre1", "booking");

        //Request Body ve expected Data ayni oldugu icin tek bir JSONObject kullanilmasi yeterlidir
        HerokuAppTestData testData = new HerokuAppTestData();
        JSONObject expectedRequestData = testData.setUpTestAndRequestData();
        System.out.println(expectedRequestData);
        //request gonder
        Response response = given().
                contentType("application/json").
                spec(spec02).
                auth().basic("admin", "password123").
                body(expectedRequestData.toString()).
                when().
                post("/{parametre1}");
        response.prettyPrint();

        //De serilalization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        Assert.assertEquals(expectedRequestData.getString("firstname"),
                ((Map) actualDataMap.get("booking")).get("firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"),
                ((Map) actualDataMap.get("booking")).get("lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"),
                ((Map) actualDataMap.get("booking")).get("totalprice"));
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),
                ((Map) actualDataMap.get("booking")).get("depositpaid"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));


        //JsonPath Yontemi
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedRequestData.getString("firstname"),
                json.getString("booking.firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"),
                json.getString("booking.lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"),
                json.getInt("booking.totalprice"));
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),
                json.getBoolean("booking.depositpaid"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                json.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                json.getString("booking.bookingdates.checkout"));
    }
}


