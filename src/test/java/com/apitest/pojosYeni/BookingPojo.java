package com.apitest.pojosYeni;

public class BookingPojo {
    private Integer bookingId;
    private Booking booking;

    /**
     * No args constructor for use in serialization
     *
     */
    public BookingPojo() {
    }

    /**
     *
     * @param booking
     * @param bookingId
     */
    public BookingPojo(Integer bookingId, Booking booking) {
        super();
        this.bookingId = bookingId;
        this.booking = booking;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BookingPojo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bookingId");
        sb.append('=');
        sb.append(((this.bookingId == null)?"<null>":this.bookingId));
        sb.append(',');
        sb.append("booking");
        sb.append('=');
        sb.append(((this.booking == null)?"<null>":this.booking));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
