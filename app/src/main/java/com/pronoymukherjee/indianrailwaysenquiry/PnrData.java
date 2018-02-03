package com.pronoymukherjee.indianrailwaysenquiry;

/**
 * Created by pronoymukherjee on 02/02/18.
 */

public class PnrData {
    String currentStatus,bookingStatus,chartStatus,jounrneyClass;
    public PnrData(String currentStatus,String bookingStatus,String chartStatus,String jounrneyClass){
        this.bookingStatus=bookingStatus;
        this.currentStatus=currentStatus;
        this.chartStatus=chartStatus;
        this.jounrneyClass=jounrneyClass;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public String getChartStatus() {
        return chartStatus;
    }

    public String getJounrneyClass() {
        return jounrneyClass;
    }
}
