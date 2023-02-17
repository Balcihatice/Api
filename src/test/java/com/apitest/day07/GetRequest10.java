package com.apitest.day07;

import com.apitest.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends DummyTestBase {
    /*https://dummy.restapiexample.com/api/v1/employees urlsine istek gonderildiginde
    statuscode 200,
    1- 10 dan buyuk tum id leri ekrana yazdirin ve
    10 dan buyuk 14 id oldugunu
    2- 30 dan kucuk tum yaslari ekrana yazdirin ve
    bu yaslarin icerisinde en buyuk yasin 23 oldugunu
    3- Maasi 350000 den buyuk olan tum emoloyee nameleri ekrana yazddirin ve
    bunlarin icerisinde "Charde Marshall" oldugunu test edin


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
        //Groovy dili javanin alt dilidir,Biz bu dil yardimiyla loop kullanmadan
        //gelen responsedaki degerleri bir sarta bagli olarak listeye yazdirabiliriz


        Assert.assertEquals(200, response.statusCode());

        List<Integer> idList = jsonPath.getList("data.findAll{it.id>10}.id");//datadaki 10 dan buyuk idleri getir
        System.out.println(idList);
        Assert.assertEquals(14, idList.size());

        List<Integer> ageList = jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        Collections.shuffle(ageList);
        ageList.get(ageList.size() - 1);//en sonda bulunan yas
        Assert.assertEquals((Integer)23, ageList.get(ageList.size() - 1));


        List<String> nameList = jsonPath.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(nameList);
        Assert.assertTrue(nameList.contains("Charde Marshall"));


    }
}