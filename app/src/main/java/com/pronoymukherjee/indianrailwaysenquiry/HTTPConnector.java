package com.pronoymukherjee.indianrailwaysenquiry;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by pronoymukherjee on 28/01/18.
 */

public class HTTPConnector {
    private String TAG=HTTPConnector.class.getSimpleName();
    private   String queryURL;
    private JSONObject jsonResponse;
    private Context context;
    private ProgressBar progressBar;
    public HTTPConnector(Context context,String queryURL,ProgressBar progressBar){
        this.context=context;
        this.queryURL=queryURL;
        this.progressBar=progressBar;
    }
    public JSONObject getJsonResponse(){
        makequery();
        progressBar.setVisibility(View.VISIBLE);
        return jsonResponse;
    }
    private void makequery(){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, queryURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                jsonResponse=response;
                progressBar.setVisibility(View.GONE);
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
