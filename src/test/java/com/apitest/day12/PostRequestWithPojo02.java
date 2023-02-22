package com.apitest.day12;

import com.apitest.Pojos.BookingDatesPojo;
import com.apitest.Pojos.BookingPojo;
import com.apitest.Pojos.BookingResponsePojo;
import com.apitest.testBase.HerokuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostRequestWithPojo02 extends HerokuAppTestBase {

    /*
https://www.resfulful-booker.herokuapp.com/booking url ine asagidaki request gonderildiginde
 requestbody{
             "firstname" "Sally"
             "lastname":"Smith"
             "totalprice":667
             "depositpaid":true,
             "bookingdates": {
        "checkin":"2018-07-27"
        "checkout":"2019-10-20" gonderildigin de Donen responsun


        {
        "bookingId": 11,
        "booking":{
            "firstname" "Sally"
             "lastname":"Smith"
             "totalprice":667
             "depositpaid":true,
             "bookingdates": {
                    "checkin":"2018-07-27"
                    "checkout":"2019-10-20"
             }
            }
           } oldugunu ddogrulayin
 */

    @Test
    public void test() {
        //url
        spec02.pathParam("parametre1", "booking");

        //testdatasi olustur
        BookingDatesPojo bookingdates = new BookingDatesPojo("2018-07-27", "2019-10-20");
        BookingPojo bookingexpectedPojo = new BookingPojo("Sally", "Smith", 677, true, bookingdates);

        System.out.println(bookingexpectedPojo);

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec02).
                auth().
                basic("admin", "password123").
                body(bookingexpectedPojo).
                when().
                post("/{parametre1}");
        response.prettyPrint();

        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        //burada response den donen yapiyi actual dataya atadik

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(bookingexpectedPojo.getFirstname(), actualData.getBooking().getFirstname());
        Assert.assertEquals(bookingexpectedPojo.getLastname(), actualData.getBooking().getLastname());
        Assert.assertEquals(bookingexpectedPojo.getTotalprice(), actualData.getBooking().getTotalprice());
        Assert.assertEquals(bookingexpectedPojo.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(bookingexpectedPojo.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());


    }

}
