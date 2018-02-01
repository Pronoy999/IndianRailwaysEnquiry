package com.pronoymukherjee.indianrailwaysenquiry;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by pronoymukherjee on 23/01/18.
 */

public class Messages {
    /**
     * This is the method to make Log Messages.
     * @param TAG:The LOG TAG.
     * @param MSG: The LOG Message.
     */
    public static void logMessage(String TAG,String MSG){
        Log.d(TAG,MSG);
    }

    /**
     * This is the method to generate the Toast Messages.
     * @param context: The application context.
     * @param msg: The message which is to be displayed.
     * @param length: The duration of the Toast Message. Pass NULL for short.
     */
    public static void toastMessge(Context context,String msg, String length){
        if(length.equals("long")){
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
