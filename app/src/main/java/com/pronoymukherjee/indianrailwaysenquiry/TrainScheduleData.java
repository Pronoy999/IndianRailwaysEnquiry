package com.pronoymukherjee.indianrailwaysenquiry;

/**
 * Created by Sayandeep on 04-02-2018.
 */

public class TrainScheduleData {
    String stationName,arrival,departure;
    int halt,days;
    double distance;
    public TrainScheduleData(String stationName,String arrival,String departure,int halt,int days,double distance)
    {
        this.stationName=stationName;
        this.distance=distance;
        this.arrival=arrival;
        this.departure=departure;
        this.halt=halt;
        this.days=days;
    }

    public String getStationName() {
        return stationName;
    }

    public String getDistance() {
        return String.valueOf(distance);
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDays() {
        return String.valueOf(days);
    }

    public String getHalt() {
        return String.valueOf(halt);
    }
}
