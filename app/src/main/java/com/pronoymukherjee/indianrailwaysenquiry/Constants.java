package com.pronoymukherjee.indianrailwaysenquiry;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the class which stores all the Constants that are used throughout the code.
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

    public static String STATION_NAME_CODE_URL="https://api.railwayapi.com/v2/name-to-code/station/<stn name>/apikey/xnlo6zq3yj/";
    public static final String RESPONSE_CODE="response_code";
    public static final String TRAIN_NAME="name";
    public static final String TRAIN_NUMBER="number";
    public static final String TRAIN="train";
    public static final String TRAIN_POSITION="position";
    public static final String TRAIN_ROUTE="route";
    public static final String TRAIN_DAYS="days";
    public static final String DAY_TS="day";
    public static final String DISTANCE_TS="distance";
    public static final String STATION_ARRIVAL_TS="scharr";
    public static final String STATION_DEPARTURE_TS="schdep";
    public static final String STATION_TS="station";

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


    public static final String ERROR_MESSAGE_INTERNET ="Ops! Your connection is wonky.";
    public static final String ERROR_MESSAGE_GENERAL="Something went wrong!";
    public static final String COPY_LABEL="copiedText";
    public static final String COPIED_TEXT_MESSAGE="Station Code  copied!";

    public static Map<Integer,String> responseCodeDesc=new HashMap<>();
    public static Map<String,String> classCode=new HashMap<>();
    public static Map<String,String> quotaCode=new HashMap<>();
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

    /**
     * This is the method to fill the hashmap with the class code.
     */
    public static void fillClassCode(){
         classCode.put("1A","First Class AC.");
         classCode.put("2A","AC-2 tier sleeper.");
         classCode.put("FC","First Class");
         classCode.put("3A","AC-3 tier sleeper.");
         classCode.put("3E","AC-3 tier economy.");
         classCode.put("CC","AC Chair car.");
         classCode.put("SL","Sleeper class.");
         classCode.put("2S","Second sitting.");
     }

    /**
     * This the method to fill the Quota Codes.
     */
    public static void fillQuotaCode(){
        quotaCode.put("GN","General Quota");
        quotaCode.put("LD","Ladies Quota");
        quotaCode.put("HO","HeadQuaters/High Official Quota");
        quotaCode.put("DF","Defence Quota");
        quotaCode.put("PH","Parliament house Quota");
        quotaCode.put("FT","Foreign Tourist Quota");
        quotaCode.put("DP","Duty Pass Quota");
        quotaCode.put("TQ","Tatkal Quota");
        quotaCode.put("PT","Premium Tatkal Quota");
        quotaCode.put("SS","Female(above 45 year)Senior Citizen/Travelling Alone");
        quotaCode.put("HP","Physically Handicapped Quota");
        quotaCode.put("RE","Railway employee on Duty for the train");
        quotaCode.put("GNRS","General Quota Roadside");
        quotaCode.put("OS","Out Station");
        quotaCode.put("PQ","Pooled Quota");
        quotaCode.put("RC(RAC)","Reserved against Cancellation");
        quotaCode.put("RS","RoadSide");
        quotaCode.put("YU","Yuva");
        quotaCode.put("LB","Lower Berth");
     }
}
