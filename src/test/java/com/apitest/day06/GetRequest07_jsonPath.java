package com.apitest.day06;

import com.apitest.testBase.HerokuAppTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest07_jsonPath extends HerokuAppTestBase {
    //https://restful-booker.herokuapp.com/booking/5 adresine bir request gonderildiginde
    //accept type`i "application/json" olan GET reueat yolladigimda gelen response`un
    //asagidaki gibi oldugunu test edin
    //{"firstname":Sally,
    //"lastname":"smith",
    //totalprice":"789,
    //"depositpaid":false
    //"bookingdates":{"checkin":"2017-12-11",
    //                "checkout":"2020-02-20"}


    //Response.jsonPath();Jsonpath clasindan obje ureterek response uzerinden JsonPath
    //classindan methodlari kullanmamizi saglar.
    @Test
    public void test01() {
        //String url yerine testBase den spec kullanmaya basladik
        spec02.pathParams("parametre1", "booking",
                "parametre2", 5);

        //request olusturalim
        Response response = given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        // Assert.assertEquals(200,response.getStatusCode());

        //jsonPath den bir obje tanimlamaliyiz
        JsonPath jsonPath = response.jsonPath();//response dan geleni al jsonpathe aktar
        //jsonpath bize sadece body dondurur,statuscode ve header alamayiz
        Assert.assertEquals("Eric", jsonPath.getString("firstname"));
        Assert.assertEquals("Jackson", jsonPath.getString("lastname"));
        Assert.assertEquals(418, jsonPath.getInt("totalprice"));
        Assert.assertEquals(false, jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals("2021-06-23", jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals("2021-06-30", jsonPath.getString("bookingdates.checkout"));


    }
}
