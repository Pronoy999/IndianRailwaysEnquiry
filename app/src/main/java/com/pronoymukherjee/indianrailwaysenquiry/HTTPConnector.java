package com.pronoymukherjee.indianrailwaysenquiry;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.pronoymukherjee.indianrailwaysenquiry.activity.StationNameToCode;

import org.json.JSONObject;

/**
 * This is the class to create a Volley Request.
 */

public class HTTPConnector {
    private String TAG=HTTPConnector.class.getSimpleName();
    private  String queryURL;
    public JSONObject jsonResponse;
    private Context context;
    private ProgressBar progressBar;
    public HTTPConnector(Context context,String queryURL,ProgressBar progressBar){
        this.context=context;
        this.queryURL=queryURL;
        this.progressBar=progressBar;
    }
    public void makeQuery(){
        makequery();
        progressBar.setVisibility(View.VISIBLE);
    }
    public boolean getJsonResponse(){
        return true;
    }
    private void makequery(){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, queryURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                jsonResponse=response;
                progressBar.setVisibility(View.GONE);
                StationNameToCode.updateStatus();
                //TODO:Make the Switch case for every class to call the Update method.
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
