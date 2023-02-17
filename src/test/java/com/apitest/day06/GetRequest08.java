package com.apitest.day06;

import com.apitest.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends DummyTestBase {

    //https://dummy.restapiexample.com/api/v1/employees urlinde bulunan
    //1- Bu calisanlarin isimlerini konsola yazdiralim
    //2- 3.calisan kisinin ismini konsola yazdiralim
    //3- ilk 5 calisanin adini konsola yazdiralim
    //4- En son calisanin adini konsola yazdiralim

    @Test
    public void test() {

        spec03.pathParam("parametre1", "employees");

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        response.prettyPrint();

        //data.employee_name dersek liste atmadan tum isimleri yazdirabiliriz
        //eger bir liste alacaksan getList ile alabiliriz
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getList("data.employee_name"));
        //System.out.println(jsonPath.getString("data.employee_name"));

        //ikinci kisinin ismi
        System.out.println(jsonPath.getString("data[2].employee_name"));
        //System.out.println(jsonPath.getString("data.employee_name[2]"));sekliylede calisir

        //ilk 5 isim
        System.out.println(jsonPath.getString("data.employee_name[10,1,2,3,4]"));

        //son calisanin adini
        System.out.println(jsonPath.getString("data.employee_name[-1]"));

        //Assertionlarini yazalim-> 3. kisinin adini "Ashton Cox" oldugunu test edelim
        Assert.assertEquals("Ashton Cox", jsonPath.getString("data[2].employee_name"));
        Assert.assertEquals("Doris Wilder", jsonPath.getString("data.employee_name[-1]"));

        //statuscode jsondan test edilmez. o yuzden responsdan assert ettik
        Assert.assertEquals(200, response.statusCode());
    }
}
