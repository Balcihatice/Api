package com.apitest.day14;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RegresRequest {

    @Test
    public void test() {
        //eger methodu static yaparsak direk class ismi ile ulasabiliriz
        //static methodu cagiracaksak icinde bulundugumuz yapida static olmali
        //dokumanda header da gonder derse yada queryparam ile gonder derse ona gore islem yapariz
        //Bunu postmande authorization altinda gorebiliriz
        //eger queryparam ile gondereceksek spec incinde .queryparam diyerek kullaniriz
       // spec03.pathParams("parametre1","todos").queryParam("apikey","token i yaz");
       //Bir diger yondem de header ile gondermektir
       /*  Response response = given().
                contentType("application/json").header("token",token yaz)
                body(requestBody).
                when().
                post(url);

        */
        RegresToken obje = new RegresToken();
        System.out.println(obje.tokenAl());

    }
}
