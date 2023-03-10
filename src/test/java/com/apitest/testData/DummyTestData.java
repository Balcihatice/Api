package com.apitest.testData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {
   /*
    statusCode 200
    5. calisanin isminin "Airi satou oldugunu, calisan sayisinin 24 oldugunu,
    Sondan 2. calisanin maasinin 106450 oldugunu
   40,21 ve 19 yaslarinda calisanlar olup olmadigini(yas diye bir liste atabiliriz)
  11.calisan bilgilerinin
    {
        "id":11
        "employee_name":"Jena Gaines"
        "employee_salary":"90560"
        "employee_age":"30"
        "profile_image":""
    }
    */

    public HashMap<String, Object> setUpTestData() {

        List<Integer> arananYaslar = new ArrayList<>();
        arananYaslar.add(21);
        arananYaslar.add(40);
        arananYaslar.add(19);


        HashMap<String, Object> onbirinci = new HashMap<>();
        onbirinci.put("id", 11);
        onbirinci.put("employee_name", "Jena Gaines");
        onbirinci.put("employee_salary", 90560);
        onbirinci.put("employee_age", 30);
        onbirinci.put("profile_image", "");

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("besinciCalisan", "Airi Satou");
        expectedData.put("calisanSayisi", 24);
        expectedData.put("sondanIkinciCalisanMaasi", 106450);
        expectedData.put("calsianYaslari", arananYaslar);
        expectedData.put("onbirincicalisan", onbirinci);

        return expectedData;
    }

    //getrequest14
    //https://dummy.restapiexample.com/api/v1/employees adresine bir request gonderildiginde
    //statusCode 200 oldugunu
    //En yuksek maasin 725000 oldugunu
    //En kusuk yasin 19 oldugunu
    //Ikinci en yuksek maasin 675000 oldugunu test edin
    public HashMap<String, Integer> setUpData02() {
        HashMap<String, Integer> expectedData = new HashMap<String, Integer>();
        expectedData.put("statusCode", 200);
        expectedData.put("enYuksekMaas", 725000);
        expectedData.put("enKucukYas", 19);
        expectedData.put("ikinciEnYuksekMaas", 675000);
        return expectedData;

    }

    public HashMap<String, String> setUpRequestBody() {

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "batch103");
        requestBody.put("salary", "123000");
        requestBody.put("age", "20");
        return requestBody;
    }

    public HashMap<String, Object> setUpExpectedData() {
//        HashMap<String, Object> data = new HashMap<String, Object>();
//        data.put("name", "batch103");
//        data.put("salary", "123000");
//        data.put("age", "20");


        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("statusCode", 200);
        expectedData.put("status", "success");
        //expectedData.put("data", data);
        expectedData.put("message", "Successfully! Record has been added.");
        return expectedData;
    }


    //delete request
    /*
    {
    "status": "success,
    "data": "2"
    "message": "Successfully! Record has been deleted"
    }
     */
    public JSONObject setUpDeleteExpectedData(){
        JSONObject expectedData = new JSONObject();
        expectedData.put("status","success");
        expectedData.put("data","2");
        expectedData.put("message","Successfully! Record has been deleted");
        return expectedData;
    }


}
