package com.apitest.testData;

import java.util.HashMap;

public class HerokuAppTestData {
   /*
     "firstname" "Sally"
             "lastname":"Smith"
             "totalprice":667
             "depositpaid":true,
             "bookingdates": {
        "checkin":"2018-07-27"
        "checkout":"2019-10-20"
      }

    */
    //sadece test datalarini olusturuyoryuz.
    public HashMap<String, Object> setUpTestData(){
        HashMap<String,Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2018-10-29");
        bookingDates.put("checkout", "2018-11-30");


        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Susan");
        expectedData.put("lastname", "Ericsson");
        expectedData.put("totalprice", 540.0);
        expectedData.put("depositpaid", false);
        expectedData.put("bookingdates", bookingDates);
       return expectedData;
    }

}
