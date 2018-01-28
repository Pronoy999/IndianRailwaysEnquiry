package com.pronoymukherjee.indianrailwaysenquiry;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by pronoymukherjee on 28/01/18.
 */

public class SingleTon {
    private Context context;
    private static SingleTon singleTon;
    private RequestQueue requestQueue;
    public SingleTon(Context context){
        this.context=context;
        requestQueue=getRequestQueue();
    }
    private RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(context);
        }
        return requestQueue;
    }
    public static synchronized SingleTon getInstance(Context context){
        if(singleTon==null){
            singleTon=new SingleTon(context.getApplicationContext());
        }
        return singleTon;
    }
    public void addToRequestQueue(Request request){
        requestQueue.add(request).setRetryPolicy(new DefaultRetryPolicy());
    }
}
