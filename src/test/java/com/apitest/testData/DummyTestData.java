package com.apitest.testData;

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
}
