package com.apitest.day09;

import com.apitest.testBase.DummyTestBase;
import com.apitest.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13_JsonPath extends DummyTestBase {

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

        //ilk olarak obje olusturalim
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"), json.getString("data[4].employee_name"));
        Assert.assertEquals(expectedDataMap.get("calisanSayisi"), json.getList("data.id").size());
        Assert.assertEquals(expectedDataMap.get("sondanIkinciCalisanMaasi"), json.getInt("data[-2].employee_salary"));
        Assert.assertTrue(json.getList("data.employee_age").containsAll((List) expectedDataMap.get("calsianYaslari")));
        Assert.assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("id"),
                json.getInt("data[10].id"));

        Assert.assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_name"), json
                .getString("data[10].employee_name"));

        Assert.assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_salary"),
                json.getInt("data[10].employee_salary"));

        Assert.assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_age"),
                json.getInt("data[10].employee_age"));

        Assert.assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("profile_image"),
                json.getInt("data[10].profile_image"));


    }
}