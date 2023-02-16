package com.apitest.day05;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest03 {

    //https://restful-booker.herokuapp.com/booking/7 adresine bir request gonderildiginde
    //accept type`i "application/json" olan GET reueat yolladigimda gelen response`un
    //status codeun 200
    //content-type "application/json" oldugunu test edin
    //"firstname": "Mark",
    //    "lastname": "Jones",
    //    "totalprice": 347,
    //    "depositpaid": false,
    //    "bookingdates": {
    //        "checkin": "2020-06-10",
    //        "checkout": "2022-04-10" dogrulayin
    @Test
    public void test01() {
        String url = "https://restful-booker.herokuapp.com/booking/7";
        Response response = given().
                accept("application/json").
                when().
                get(url);


        response.prettyPrint();
        //eeger status codu assert edeceksek respons.then
        //normal assert ilede yapariz

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", Matchers.equalTo("Mark")).
                body("lastname", Matchers.equalTo("Jackson")).
                body("totalprice", Matchers.equalTo(498)).
                body("depositpaid", Matchers.equalTo(false)).
                body("bookingdates.checkin", Matchers.equalTo("2021-06-07")).
                body("bookingdates.checkout", Matchers.equalTo("2021-08-25"));

    }
}
