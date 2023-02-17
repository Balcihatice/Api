package com.apitest.day06;

import com.apitest.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends DummyTestBase {

    /*https://dummy.restapiexample.com/api/v1/employees urlsine istek gonderildiginde
    statuscode 200,
    gelen body de,
    5.calisanin isminin ""oldugunu,
    6.calisanin maasinin 37200 oldugunu
    Toplam 24 calisan oldugunu
    "Rhona Davidson" in employee lerden biri oldugunu
    "21,23,61" yaslarinda employeler oldugunu test edin

    json methodlarindan getString,getInt,getList,getBoolenan kullanalim

   */

    @Test
    public void test() {

        spec03.pathParam("parametre1", "employees");

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        response.prettyPrint();


        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(200, response.getStatusCode());

        System.out.println(jsonPath.getList("data.id").size());
        Assert.assertEquals(24, jsonPath.getList("data.id").size());
        Assert.assertEquals("Airi Satou", jsonPath.getString("data[4].employee_name"));
        Assert.assertEquals(372000, jsonPath.getInt("data[5].employee_salary"));

        //    "Rhona Davidson" in employee lerden biri oldugunu
        Assert.assertTrue(jsonPath.getList("data.employee_name").contains("Rhona Davidson"));

        //"21,23,61" yaslarinda employeler oldugunu test edin
        // List<Integer> arananYaslar = Arrays.asList(21, 23, 61);//tam bir liste degil ama liste gibi gorev alir
        List<Integer> arananYaslar = new ArrayList<>();
        arananYaslar.add(21);
        arananYaslar.add(23);
        arananYaslar.add(61);
        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll(arananYaslar));


    }
}