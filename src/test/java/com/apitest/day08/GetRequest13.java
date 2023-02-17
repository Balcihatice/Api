package com.apitest.day08;

import com.apitest.testBase.DummyTestBase;
import com.apitest.testData.DummyTestData;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;


public class GetRequest13 extends DummyTestBase {

/*
https://dummy.restapiexample.com/api/v1/employees adresine bir request gonderildiginde
5. calisanin isminin "Airi satou oldugunu, calisan sayisinin 24 oldugunu,
Sondan 2. calisanin maasinin 106450 oldugunu
40,21 ve 19 yaslarinda calisanlar olup olmadigini(yas diye bir liste atabiliriz)
11.calisan bilgilerinin
{
"id":11
"employee_name":"Jena Gaines"
"employee_salary":"90560"
"eemployee_age":"30"
"profile_image":""
}
gibi oldugunu test edin
Butun bilgileri tutan map
yaslari tutan list ve diger bilgileri tutan map olmali
 */

    @Test
    public void test() {
        spec03.pathParams("parametre1", "employees");


        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedDataMap = expectedObje.setUpTestData();
        System.out.println(expectedDataMap);

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");
        response.prettyPrint();


        //
    }
}
