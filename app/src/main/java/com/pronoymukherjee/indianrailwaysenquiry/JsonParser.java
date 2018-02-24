package com.pronoymukherjee.indianrailwaysenquiry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**This is the class to Parse the different types of JSON Responses.
 */

public class JsonParser {
    private final String TAG=JsonParser.class.getSimpleName();
    private JSONObject jsonObject;
    public JsonParser(JSONObject jsonObject){
        this.jsonObject=jsonObject;
    }
    public boolean isCorrectResponse(){
        boolean isCorrect=false;
        try {
            int responseCode=jsonObject.getInt(Constants.RESPONSE_CODE);
            if(Constants.RESPONSE_CODE_DESC.get(responseCode).equalsIgnoreCase("Success"))
                isCorrect=true;

        } catch (JSONException e) {
            Messages.logMessage(TAG,e.toString());
        }
        return isCorrect;
    }
    public String getTrainName(){
        String trainName="";
        try {
            JSONObject train = jsonObject.getJSONObject(Constants.TRAIN);
            trainName=train.getString(Constants.TRAIN_NAME);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return trainName;
    }
    public String getLiveStatus(){
        String trainStatus="";
        try{
            trainStatus=jsonObject.getString(Constants.TRAIN_POSITION);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return trainStatus;
    }
    public String getPnrNumber(){
        String pnrNumber="";
        try{
            pnrNumber=jsonObject.getString(Constants.PNR_JSON);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return pnrNumber;
    }
    public String getDateofJourneyPnr(){
        String date="";
        try{
            date=jsonObject.getString(Constants.DATE_OF_JOURNEY_PNR);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return date;
    }
    public int getNumberofPassengers(){
        int passengerNumber=0;
        try{
            passengerNumber=jsonObject.getInt(Constants.TOTAL_PASSENGERS);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return passengerNumber;
    }
    public boolean getChartStatus(){
        boolean isChartPrepared=false;
        try{
            isChartPrepared=jsonObject.getBoolean(Constants.CHART_STATUS);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return isChartPrepared;
    }
    public String getFromStation(){
        String fromStation="";
        try{
            fromStation=jsonObject.getString(Constants.FROM_STATION_PNR);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return fromStation;
    }
    public String getToStation(){
        String toStation="";
        try{
            toStation=jsonObject.getString(Constants.TO_STATION_PNR);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return toStation;
    }
    public String getBoardingPoint(){
        String boardingPoint="";
        try{
            boardingPoint=jsonObject.getString(Constants.BOARDING_POINT_PNR);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return boardingPoint;
    }
    public String getReservedUpto(){
        String reservedUpto="";
        try{
            reservedUpto=jsonObject.getString(Constants.RESERVED_UPTO_PNR);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return reservedUpto;
    }
    public String getTrainNumber(){
        String trainNumber="";
        try{
            trainNumber=jsonObject.getJSONObject(Constants.TRAIN).getString(Constants.TRAIN_NUMBER);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return trainNumber;
    }
    public String[][] getPNRStatus(){
        String status[][];
        int totalPassenger=getNumberofPassengers();
        status=new String[totalPassenger][2];
        try{
            JSONArray passenger=jsonObject.getJSONArray(Constants.PASSENGERS_PNR);
            for(int i=0;i<totalPassenger;i++){
                JSONObject each=passenger.getJSONObject(i);
                status[i][0]=each.getString(Constants.BOOKING_STATUS);
                status[i][1]=each.getString(Constants.CURRENT_STATUS);
            }
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return status;
    }
    public String getJourneyClassCode(){
        String classCode="";
        try{
            classCode=jsonObject.getJSONObject(Constants.JOURNEY_CLASS_PNR).getString(Constants.JOURNEY_CLASS_CODE);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return classCode;
    }
    public String[][] getStationCodes(){
        String stationNameCode[][]=null;
        try {
            JSONArray stations=jsonObject.getJSONArray(Constants.STATION_CODE_ARRAY);
            stationNameCode=new String[stations.length()][2];
            for(int i=0;i<stations.length();i++){
                JSONObject eachStation=stations.getJSONObject(i);
                stationNameCode[i][0]=eachStation.getString(Constants.STATION_CODE_NAME);
                stationNameCode[i][1]=eachStation.getString(Constants.STATION_CODE);
            }
            //Arrays.sort(stationNameCode,(String s1[],String s2[])->s2[0].compareTo(s1[0]));

        } catch (JSONException e) {
            Messages.logMessage(TAG,e.toString());
        }
        return stationNameCode;
    }
    public String getFare(){
        String fare="";
        try{
            fare=jsonObject.getString(Constants.FARE);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return fare;
    }
    public JSONArray getRoute(){
        JSONArray route=null;
        try {
            route=jsonObject.getJSONArray(Constants.TRAIN_ROUTE);
        } catch (JSONException e) {
            Messages.logMessage(TAG,e.toString());
        }
        return route;
    }
    public JSONObject getStation(int index){
        JSONObject station=null;
        try{
            station=jsonObject.getJSONArray(Constants.STATION_TS).getJSONObject(index);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return station;
    }
    public JSONArray getDays(){
        JSONArray days=null;
        try{
            days=jsonObject.getJSONObject(Constants.TRAIN).getJSONArray(Constants.TRAIN_DAYS);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return days;
    }
    public boolean isRunningOnDay(int index,JSONArray days){
        try{
            String doesRun=days.getJSONObject(index).getString(Constants.TRAIN_DAYS_RUNS_TS);
            if(doesRun.equals("Y"))
                return true;
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return false;
    }
    public String getScheduledArrival(int index,JSONArray route){
        String schArrival="";
        try{
            JSONObject station=route.getJSONObject(index);
            schArrival=station.getString(Constants.SCHEDULE_ARRIVAL_TS);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return schArrival;
    }
    public String getScheduleDeparture(int index,JSONArray route){
        String schDept="";
        try{
            schDept=route.getJSONObject(index).getString(Constants.SCHEDULE_DEPARTURE_TS);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return schDept;
    }
    public String getScheduleDay(int index,JSONArray route){
        String schDay="";
        try{
            schDay=route.getJSONObject(index).getString(Constants.DAY_TS);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return schDay;
    }
    public String getScheduleDistance(int index,JSONArray route){
        String schDistance="";
        try{
            schDistance=route.getJSONObject(index).getString(Constants.DISTANCE_TS);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return schDistance;
    }
    public String getScheduleHalt(int index,JSONArray route){
        String schHlt="";
        try{
            schHlt=route.getJSONObject(index).getString(Constants.SCHEDULE_HALT);
        }
        catch (JSONException e){
            Messages.logMessage(TAG,e.toString());
        }
        return schHlt;
    }
}
