package com.pronoymukherjee.indianrailwaysenquiry;

import android.content.Context;

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
    public static String queryURL;
    private JSONObject jsonResponse;
    private Context context;

    public static void setQueryURL(String queryURL) {
        HTTPConnector.queryURL = queryURL;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public JSONObject getJsonResponse() {
        makeQuery();
        return jsonResponse;
    }
    private void makeQuery(){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, queryURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                jsonResponse=response;
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
