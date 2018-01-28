package com.pronoymukherjee.indianrailwaysenquiry;

/**
 * This is the class to keep all the constants.
 */

public class Constants {
    static final String API_KEY="xnlo6zq3yj";
    static String TRAIN_STATUS_URL=
            "https://api.railwayapi.com/v2/live/train/<train number>/date/<dd-mm-yyyy>/apikey/xnlo6zq3yj/";
    static String PNR_STATUS_URL="https://api.railwayapi.com/v2/pnr-status/pnr/<pnr no>/apikey/xnlo6zq3yj/";
    static String TRAIN_ROUTE_URL="https://api.railwayapi.com/v2/route/train/<train number>/apikey/xnlo6zq3yj/";
    static String SEAT_AVAILABILITY_URL=
            "https://api.railwayapi.com/v2/check-seat/train/<train number>/source/<stn code>/dest/<dest code>/date/<dd-mm-yyyy>/pref/<class code>/quota/<quota code>/apikey/xnlo6zq3yj/";
    static String TRAINS_BETWEEN_STATIONS_URL=
            "https://api.railwayapi.com/v2/between/source/<stn code>/dest/<stn code>/date/<dd-mm-yyyy>/apikey/xnlo6zq3yj/";
    static String TRAIN_NAME_NUMBER="https://api.railwayapi.com/v2/name-number/train/<name or number>/apikey/xnlo6zq3yj/";
    static String TRAIN_FARE_URL=
            "https://api.railwayapi.com/v2/fare/train/<train number>/source/<stn code>/dest/<stn code>/age/<age>/pref/<class code>/quota/<quota code>/date/<dd-mm-yyyy>/apikey/xnlo6zq3yj/";
    static String STATION_NAME_CODE="https://api.railwayapi.com/v2/name-to-code/station/<stn name>/apikey/xnlo6zq3yj/";
}
