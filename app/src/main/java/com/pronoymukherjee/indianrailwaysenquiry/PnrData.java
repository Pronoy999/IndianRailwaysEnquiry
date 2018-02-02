package com.pronoymukherjee.indianrailwaysenquiry;

/**
 * Created by pronoymukherjee on 02/02/18.
 */

public class PnrData {
    String currentStatus,bookingStatus;
    public PnrData(String currentStatus,String bookingStatus){
        this.bookingStatus=bookingStatus;
        this.currentStatus=currentStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }
}
