package com.pronoymukherjee.indianrailwaysenquiry;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pronoymukherjee on 03/02/18.
 */

public class PnrAdapter extends ArrayAdapter {
    ArrayList<PnrData> list;
    public PnrAdapter(Activity context, ArrayList<PnrData> list){
        super(context,0,list);
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem=convertView;
        if(listItem==null){
            listItem= LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item,parent,false);
        }
        TextView bookingStatus=listItem.findViewById(R.id.bookingStatus);
        TextView currentStatus=listItem.findViewById(R.id.currentStatus);
        TextView chartStatus=listItem.findViewById(R.id.chartStatus);
        TextView jounreyClass=listItem.findViewById(R.id.journeyClass);
        PnrData pnrData=list.get(position);
        bookingStatus.setText(pnrData.getBookingStatus());
        currentStatus.setText(pnrData.getCurrentStatus());
        chartStatus.setText(pnrData.getChartStatus());
        jounreyClass.setText(pnrData.getJounrneyClass());
        return listItem;
    }
}
