package com.apitest.day14;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RegresToken {
    //kullanici adi ve sifre gondeririz oda bize token verir.bunu post ile yapariz
    //Ayrica kullanici adi ve sifre ye izin verilmis olmalidir.
    //extend e gerek yok cunku bir kere kullanacagiz
    //Sirkettte daha cok kullanirsak testbase olusturmaliyiz

    public String tokenAl() {
        String url = "https://reqres.in/api/login";
        //Test datasi olusturalim

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityslicka");
        //System.out.println(requestBody);

        Response response = given().
                contentType("application/json").
                body(requestBody).
                when().
                post(url);
        //response.prettyPrint();

        JsonPath json  = response.jsonPath();
        String token = json.getString("token");
        return token;
    }
}
