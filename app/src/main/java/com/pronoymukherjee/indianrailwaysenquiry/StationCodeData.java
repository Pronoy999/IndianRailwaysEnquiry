package com.pronoymukherjee.indianrailwaysenquiry;

/**
 * Created by mukhe on 17-Feb-18.
 */

public class StationCodeData {
    String stationName,stationCode;
    public StationCodeData(String stationName,String stationCode){
        this.stationName=stationName;
        this.stationCode=stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationCode() {
        return stationCode;
    }
}
