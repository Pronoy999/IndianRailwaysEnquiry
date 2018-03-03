package com.pronoymukherjee.indianrailwaysenquiry;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the class which stores all the Constants that are used throughout the code.
 */

public class Constants {
    public static  final String API_KEY="xnlo6zq3yj";
    public static String TRAIN_STATUS_URL=
            "https://api.railwayapi.com/v2/live/train/<train number>/date/<dd-mm-yyyy>/" +
                    "apikey/xnlo6zq3yj/";
    public static String PNR_STATUS_URL="https://api.railwayapi.com/v2/pnr-status/pnr/<pnr no>/" +
            "apikey/xnlo6zq3yj/";
    public static String TRAIN_ROUTE_URL="https://api.railwayapi.com/v2/route/train/<train number>/" +
            "apikey/xnlo6zq3yj/";
    public static String SEAT_AVAILABILITY_URL=
            "https://api.railwayapi.com/v2/check-seat/train/<train number>/source/<stn code>/dest/" +
                    "<dest code>/date/<dd-mm-yyyy>/pref/<class code>/quota/<quota code>/apikey/xnlo6zq3yj/";
    public static String TRAINS_BETWEEN_STATIONS_URL=
            "https://api.railwayapi.com/v2/between/source/<stn code>/dest/<stn code>/date/<dd-mm-yyyy>/apikey/xnlo6zq3yj/";
    public static String TRAIN_NAME_NUMBER="https://api.railwayapi.com/v2/name-number/train/<name or number>/apikey/xnlo6zq3yj/";
    public static String TRAIN_FARE_URL=
            "https://api.railwayapi.com/v2/fare/train/<train number>/source/<stn code>/dest/<stn code>" +
                    "/age/<age>/pref/<class code>/quota/<quota code>/date/<dd-mm-yyyy>/apikey/xnlo6zq3yj/";

    public static String STATION_NAME_CODE_URL="https://api.railwayapi.com/v2/name-to-code/station/<stn name>/apikey/xnlo6zq3yj/";
    public static final String RESPONSE_CODE="response_code";
    public static final String TRAIN_NAME="name";
    public static final String TRAIN_NUMBER="number";
    public static final String TRAIN="train";
    public static final String TRAIN_POSITION="position";
    public static final String TRAIN_ROUTE="route";
    public static final String TRAIN_DAYS="days";

    public static final String TRAIN_DAYS_CODE_TS="code";
    public static final String TRAIN_DAYS_RUNS_TS="runs";
    public static final String DAY_TS="day";
    public static final String DISTANCE_TS="distance";
    public static final String SCHEDULE_ARRIVAL_TS ="scharr";
    public static final String SCHEDULE_DEPARTURE_TS ="schdep";
    public static final String STATION_TS="station";
    public static final String SCHEDULE_HALT="halt";

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

    public static final String STATION_CODE_ARRAY="stations";
    public static final String STATION_CODE="code";
    public static final String STATION_CODE_NAME="name";

    public static final String FARE="fare";


    public static final String ERROR_MESSAGE_INTERNET ="Ops! Your connection is wonky.";
    public static final String ERROR_MESSAGE_GENERAL="Something went wrong!";
    public static final String COPY_LABEL="copiedText";
    public static final String COPIED_TEXT_MESSAGE="Station Code  copied!";

    public static final String GRID_TEXT_OPTIONS[]={
            "Seat Avialbility",
            "Fare Enquiry",
            "PNR Status",
            "Train Schedule",
            "Station Name to Code",
            "Train Live Status"
    };
    public static final int GRID_IMAGE_ID[]={
            R.drawable.seat,
            R.drawable.fare,
            R.drawable.pnr,
            R.drawable.train,
            R.drawable.station_code,
            R.drawable.live_status
    };

    public static Map<Integer,String> RESPONSE_CODE_DESC =new HashMap<>();
    public static Map<String,String> CLASS_CODE =new HashMap<>();
    public static Map<String,String> QUOTA_CODE =new HashMap<>();
    /**
     * This is the method to fill the hashmap to store all the response code.
    */
     public static void fillresponseCodeDesc() {
         RESPONSE_CODE_DESC.put(200, "Success");
         RESPONSE_CODE_DESC.put(210, "Train doesn't run on the date queried");
         RESPONSE_CODE_DESC.put(211, "Train doesn't have journey class queried");
         RESPONSE_CODE_DESC.put(220, "Flushed PNR");
         RESPONSE_CODE_DESC.put(221, "Invalid PNR");
         RESPONSE_CODE_DESC.put(230, "Date chosen for the query is not valid for chosen parameters");
         RESPONSE_CODE_DESC.put(404, "No Data Available");
         RESPONSE_CODE_DESC.put(405, "Request couldn't go through");
         RESPONSE_CODE_DESC.put(502, "Invalid Arguments");
     }

    /**
     * This is the method to fill the hashmap with the class code.
     */
    public static void fillClassCode(){
         CLASS_CODE.put("1A","First Class AC.");
         CLASS_CODE.put("2A","AC-2 tier sleeper.");
         CLASS_CODE.put("FC","First Class");
         CLASS_CODE.put("3A","AC-3 tier sleeper.");
         CLASS_CODE.put("3E","AC-3 tier economy.");
         CLASS_CODE.put("CC","AC Chair car.");
         CLASS_CODE.put("SL","Sleeper class.");
         CLASS_CODE.put("2S","Second sitting.");
     }

    /**
     * This the method to fill the Quota Codes.
     */
    public static void fillQuotaCode(){
        QUOTA_CODE.put("GN","General Quota");
        QUOTA_CODE.put("LD","Ladies Quota");
        QUOTA_CODE.put("HO","HeadQuaters/High Official Quota");
        QUOTA_CODE.put("DF","Defence Quota");
        QUOTA_CODE.put("PH","Parliament house Quota");
        QUOTA_CODE.put("FT","Foreign Tourist Quota");
        QUOTA_CODE.put("DP","Duty Pass Quota");
        QUOTA_CODE.put("TQ","Tatkal Quota");
        QUOTA_CODE.put("PT","Premium Tatkal Quota");
        QUOTA_CODE.put("SS","Female(above 45 year)Senior Citizen/Travelling Alone");
        QUOTA_CODE.put("HP","Physically Handicapped Quota");
        QUOTA_CODE.put("RE","Railway employee on Duty for the train");
        QUOTA_CODE.put("GNRS","General Quota Roadside");
        QUOTA_CODE.put("OS","Out Station");
        QUOTA_CODE.put("PQ","Pooled Quota");
        QUOTA_CODE.put("RC(RAC)","Reserved against Cancellation");
        QUOTA_CODE.put("RS","RoadSide");
        QUOTA_CODE.put("YU","Yuva");
        QUOTA_CODE.put("LB","Lower Berth");
     }
}
