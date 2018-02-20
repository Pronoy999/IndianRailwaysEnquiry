package com.pronoymukherjee.indianrailwaysenquiry;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.pronoymukherjee.indianrailwaysenquiry.activity.PNRActivity;
import com.pronoymukherjee.indianrailwaysenquiry.activity.StationNameToCode;

import org.json.JSONObject;

/**
 * This is the class to create a Volley Request.
 */

public class HTTPConnector {
    private String TAG=HTTPConnector.class.getSimpleName();
    private  String queryURL;
    private JSONObject jsonResponse;
    private Context context;
    private ResponseListener responseListener;
    public interface ResponseListener{
        void sendResponse(JSONObject responseObject);
    }
    public HTTPConnector(Context context,String queryURL,ResponseListener responseListener){
        this.context=context;
        this.queryURL=queryURL;
        this.responseListener=responseListener;
    }
    public void makeQuery(){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, queryURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                jsonResponse=response;
                if (responseListener!=null){
                    responseListener.sendResponse(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response=error.networkResponse;
                Messages.logMessage(TAG,response.toString());
            }
        });
        SingleTon.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
