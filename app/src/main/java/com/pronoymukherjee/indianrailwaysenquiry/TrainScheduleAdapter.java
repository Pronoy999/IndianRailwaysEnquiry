package com.pronoymukherjee.indianrailwaysenquiry;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sayandeep on 04-02-2018.
 */

public class TrainScheduleAdapter extends ArrayAdapter {
    ArrayList<TrainScheduleData> routeList;
    public TrainScheduleAdapter(Activity context,ArrayList<TrainScheduleData> routeList) {
        super(context, 0,routeList);
        this.routeList=routeList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem=convertView;
        if(listItem==null)
            listItem= LayoutInflater.from(getContext()).inflate(R.layout.custom_train_schedule_list,parent,false);
        TextView stationName=listItem.findViewById(R.id.tvStationName);
        TextView distance=listItem.findViewById(R.id.tvDistance);
        TextView arrival=listItem.findViewById(R.id.tvArrivalTime);
        TextView halt=listItem.findViewById(R.id.tvHaltTime);
        TextView departure=listItem.findViewById(R.id.tvDepartureTime);
        TextView days=listItem.findViewById(R.id.tvDay);
        TrainScheduleData trainScheduleData=routeList.get(position);
        stationName.setText(trainScheduleData.getStationName());
        distance.setText(trainScheduleData.getDistance());
        arrival.setText(trainScheduleData.getArrival());
        halt.setText(trainScheduleData.getHalt());
        departure.setText(trainScheduleData.getDeparture());
        days.setText(trainScheduleData.getDays());
        return listItem;
    }
}
