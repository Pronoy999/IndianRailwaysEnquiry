package com.pronoymukherjee.indianrailwaysenquiry;

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
}
