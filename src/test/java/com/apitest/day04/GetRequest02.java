package com.apitest.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class  GetRequest02 {

    @Test
    public void testGetRequest() {
        //https://restful-booker.herokuapp.com/booking adresine bir request gonderildiginde
        //accept type`i "application/json" olan GET reueat yolladigimda gelen response`un
        //status codeun 200
        //content-type "application/json" oldugunu test edin


        //url olustur
        String url = "https://restful-booker.herokuapp.com/booking";
        //expected data olustur-> body gerekmedigi icin olusturmuyoruz
        Response responce = given().
                accept("application/json").
                when().
                get(url);

        responce.prettyPrint();//gormek icin yazdirdik

        //dogrulama yapalim
        responce.then().
                assertThat().
                statusCode(200).
                contentType("application/json");

    }
}
