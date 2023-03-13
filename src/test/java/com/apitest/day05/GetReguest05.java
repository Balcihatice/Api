package com.apitest.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetReguest05 {
    //https://dummy.restapiexample.com/api/v1/employees adresine bir request gonderildiginde
    //accept type`i "application/json" olan GET reueat yolladigimda gelen response`un
    //status codeun 200
    //content-type "application/json" oldugunu test edin
    //ve employees sayisinin 24
    // ve employeelerden birinin "Ashton Cox"
    // ve gelen yaslar icinde 21 61 ve 23 degerlerinden birinin oldugunu test edin

    @Test
    public void test01() {
        String url = "https://dummy.restapiexample.com/api/v1/employees";
        Response response = given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id", hasSize(24),
                        "data.employee_name", hasItem("Tiger Nixon"),//boyle bir iteme degere sahip mi?
                        "data.employee_age", hasItems(21,61,23));  //hasItem bir tane deger ariyorsak kullaniliriz

    }
}