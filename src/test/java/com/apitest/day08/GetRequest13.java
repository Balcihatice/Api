package com.apitest.day08;

import com.apitest.testBase.DummyTestBase;
import com.apitest.testData.DummyTestData;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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
        //response.prettyPrint();


        //De Serialization islemi
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        //status kodun 200 oldugu dogrula
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        //5. calisanin isminin "Airi satou oldugunu
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"),
                ((Map) ((List) actualDataMap.get("data")).get(4)).get("employee_name"));

        //calisan sayisinin 24 oldugunu,
        Assert.assertEquals(expectedDataMap.get("calisanSayisi"),
                ((List<?>) actualDataMap.get("data")).size());

        //Sondan 2. calisanin maasinin 106450 oldugunu

        // Once actualdata dan bize donen listin size ini almaliyiz
        int dataSize = ((List<?>) actualDataMap.get("data")).size();

        Assert.assertEquals(expectedDataMap.get("sondanIkinciCalisanMaasi"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(dataSize - 2)).get("employee_salary"));

        //40,21 ve 19 yaslarinda calisanlar olup olmadigini
        List<Integer> actualAge = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            actualAge.add((int) ((Map) ((List<?>) actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        Assert.assertTrue(actualAge.containsAll((List) expectedDataMap.get("arananYaslar")));

        //11.calisan bilgilerinin
        //{
        //"id":11
        //"employee_name":"Jena Gaines"
        //"employee_salary":"90560"
        //"eemployee_age":"30"
        //"profile_image":""
        //}

        Assert.assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_name"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_name"));

        Assert.assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_salary"),
                ((Map) ((List) actualDataMap.get("data")).get(10)).get("employee_salary"));

        Assert.assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_age"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age"));

        Assert.assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("profile_image"),
                ((Map) ((List)actualDataMap.get("data")).get(10)).get("profile_image"));



        //
    }
}
