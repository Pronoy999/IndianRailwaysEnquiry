package com.pronoymukherjee.indianrailwaysenquiry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pronoymukherjee on 02/02/18.
 */

public class Constants {
    public static  final String API_KEY="xnlo6zq3yj";
    public static String TRAIN_STATUS_URL=
            "https://api.railwayapi.com/v2/live/train/<train number>/date/<dd-mm-yyyy>/apikey/xnlo6zq3yj/";
    public static String PNR_STATUS_URL="https://api.railwayapi.com/v2/pnr-status/pnr/<pnr no>/apikey/xnlo6zq3yj/";
    public static String TRAIN_ROUTE_URL="https://api.railwayapi.com/v2/route/train/<train number>/apikey/xnlo6zq3yj/";
    public static String SEAT_AVAILABILITY_URL=
            "https://api.railwayapi.com/v2/check-seat/train/<train number>/source/<stn code>/dest/<dest code>/date/<dd-mm-yyyy>/pref/<class code>/quota/<quota code>/apikey/xnlo6zq3yj/";
    public static String TRAINS_BETWEEN_STATIONS_URL=
            "https://api.railwayapi.com/v2/between/source/<stn code>/dest/<stn code>/date/<dd-mm-yyyy>/apikey/xnlo6zq3yj/";
    public static String TRAIN_NAME_NUMBER="https://api.railwayapi.com/v2/name-number/train/<name or number>/apikey/xnlo6zq3yj/";
    public static String TRAIN_FARE_URL=
            "https://api.railwayapi.com/v2/fare/train/<train number>/source/<stn code>/dest/<stn code>/age/<age>/pref/<class code>/quota/<quota code>/date/<dd-mm-yyyy>/apikey/xnlo6zq3yj/";

    public static String STATION_NAME_CODE="https://api.railwayapi.com/v2/name-to-code/station/<stn name>/apikey/xnlo6zq3yj/";
    public static final String RESPONSE_CODE="response_code";
    public static final String TRAIN_NAME="name";
    public static final String TRAIN_NUMBER="number";
    public static final String TRAIN="train";
    public static final String TRAIN_POSITION="position";
    public static final String TRAIN_ROUTE="route";
    public static final String TRAIN_DAYS="days";
    public static final String STATION_ARRIVAL="scharr";
    public static final String STATION_DEPARTURE="schdep";
    public static final String STATION_Name="station";
    public static final String PNR_JSON="pnr";
    public static final String DATE_OF_JOURNEY_PNR="doj";
    public static final String CHART_STATUS="chart_prepared";
    public static final String FROM_STATION_PNR="from_station";
    public static final String TO_STATION_PNR="to_station";
    public static final String BOARDING_POINT_PNR="boarding_point";
    public static final String RESERVED_UPTO_PNR="reservation_upto";
    public static final String JOURNEY_CLASS_PNR="journey_class";
    public static final String PASSENGERS_PNR="passengers";
    public static final String CURRENT_STATUS="current_status";
    public static final String BOOKING_STATUS="booking_status";
    public static final String TOTAL_PASSENGERS="total_passengers";
    public static final String JOURNEY_CLASS_CODE="code";


    static Map<Integer,String> responseCodeDesc=new HashMap<>();
    /**
     * This is the method to fill the hashmap to store all the response code.
    */
     public static void fillresponseCodeDesc() {
         responseCodeDesc.put(200, "Success");
         responseCodeDesc.put(210, "Train doesn't run on the date queried");
         responseCodeDesc.put(211, "Train doesn't have journey class queried");
         responseCodeDesc.put(220, "Flushed PNR");
         responseCodeDesc.put(221, "Invalid PNR");
         responseCodeDesc.put(230, "Date chosen for the query is not valid for chosen parameters");
         responseCodeDesc.put(404, "No Data Available");
         responseCodeDesc.put(405, "Request couldn't go through");
         responseCodeDesc.put(502, "Invalid Arguments");
     }
}
