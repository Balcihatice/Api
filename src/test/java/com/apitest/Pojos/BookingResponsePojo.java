package com.apitest.Pojos;

public class BookingResponsePojo {
    /*
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
           }
     */
    private int bookingid;
    private BookingPojo booking;

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    public BookingResponsePojo() {
    }

    public BookingResponsePojo(int bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
