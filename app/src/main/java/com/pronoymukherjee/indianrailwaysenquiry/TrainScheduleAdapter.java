package com.pronoymukherjee.indianrailwaysenquiry;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Sayandeep on 04-02-2018.
 */

public class TrainScheduleAdapter extends ArrayAdapter {
    ArrayList<TrainScheduleData> routeList;
    public TrainScheduleAdapter(Activity context,ArrayList<TrainScheduleData> routeList) {
        super(context, 0,routeList);
    }
}
