package com.apitest.day09;

import com.apitest.testBase.DummyTestBase;
import com.apitest.testData.DummyTestData;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends DummyTestBase {

    //https://dummy.restapiexample.com/api/v1/employees adresine bir request gonderildiginde
    //statusCode 200 oldugunu
    //En yuksek maasin 725000 oldugunu
    //Ikinci en yuksek maasin 675000 oldugunu test edin
    //En kusuk yasin 19 oldugunu
    @Test
    public void test() {

        spec03.pathParam("parametre1", "employees");
        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Integer> expectedDataMap = expectedObje.setUpData02();
        System.out.println(expectedDataMap);

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");


        response.prettyPrint();

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);//response.as(HashMap.class);desteriliztion kullanmak ici yazdirk
        //kalip olarak bu classi kullan der gibi
        System.out.println(actualDataMap);

        //statusCode un 200 oldugunu
        Assert.assertEquals(expectedDataMap.get("statusCode"), (Integer) response.getStatusCode());

        //En yuksek maasin 725000 oldugunu
        List<Integer> maasListesi = new ArrayList<>();
        int dataSize = ((List) actualDataMap.get("data")).size();
        for (int i = 0; i < dataSize; i++) {
            maasListesi.add((Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_salary"));
        }
        Collections.sort(maasListesi);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"), maasListesi.get(dataSize - 1));


        //ikinci en yuksek maasin 725000 oldugunu
        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"), maasListesi.get(dataSize - 2));


        //En kusuk yasin 19 oldugunu
        List<Integer> ageList = new ArrayList<Integer>();


        for (int i = 0; i < dataSize; i++) {
            ageList.add((Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        Collections.sort(ageList);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"), ageList.get(0));


    }

}
