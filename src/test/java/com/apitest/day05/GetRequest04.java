package com.apitest.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest04 {

    //https://restful-booker.herokuapp.com/booking/5 adresine bir request gonderildiginde
    //accept type`i "application/json" olan GET reueat yolladigimda gelen response`un
    //status codeun 200
    //content-type "application/json" oldugunu test edin
    //"firstname": "Sally",
    // ve totalprice in 856
    // ve checkin date`in 2015-06-12 oldugunu dogrulayin

    @Test
    public void test01() {
        String url = "https://restful-booker.herokuapp.com/booking/5";
        Response response = given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        response.then().
                assertThat().
                contentType(ContentType.JSON).
                body("firstname",equalTo("Susan"),
                        "totalprice",equalTo(239),
                        "bookingdates.checkin",equalTo("2016-07-04"));

    }

}
