package com.apitest.day09;

import com.apitest.testBase.DummyTestBase;
import com.apitest.testData.DummyTestData;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest13MatchersClass extends DummyTestBase {

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


        response.then().
                assertThat().
                statusCode((Integer) expectedDataMap.get("statusCode")).
                body("data[4].employee_name", equalTo(expectedDataMap.get("besinciCalisan")),
                        "data.id", hasSize((Integer) expectedDataMap.get("calisanSayisi")),
                        "data[-2].employee_salary", equalTo(expectedDataMap.get("sondanIkinciCalisanMaasi")),
                        "data.employee_age", hasItems(((List) expectedDataMap.get("arananYaslar")).get(0),
                                ((List<?>) expectedDataMap.get("arananYaslar")).get(1),
                                ((List<?>) expectedDataMap.get("arananYaslar")).get(2)),
                        "data[10].employee_name", equalTo(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_name")),
                        "data[10].employee_salary", equalTo(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_salary")),
                        "data[10].employee_age", equalTo(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_age")),
                        "daata[10].profile_image", equalTo(((Map) expectedDataMap.get("onbirincicalisan")).get("profile_image")));

    }
}
