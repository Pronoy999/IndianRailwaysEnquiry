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
            String response=responseCode+"";
            if(response.startsWith("2"))
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
}
