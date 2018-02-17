package com.pronoymukherjee.indianrailwaysenquiry;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mukhe on 17-Feb-18.
 */

public class StationCodeAdapter extends ArrayAdapter {
    Context context;
    ArrayList<StationCodeData> list;
    public StationCodeAdapter(Activity activity,ArrayList<StationCodeData> list){
        super(activity,0,list);
        this.context=activity;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View listView=convertView;
        if(convertView==null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.custom_station_code,parent,false);
            TextView _stationName=listView.findViewById(R.id.stationName);
            TextView _stationCode=listView.findViewById(R.id.stationCodeShow);
            final StationCodeData stationCodeData=list.get(position);
            Button copyButton=listView.findViewById(R.id.copyButton);
            _stationCode.setText(stationCodeData.stationCode);
            _stationName.setText(stationCodeData.getStationName());
            copyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipboardManager clipboardManager=(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData=ClipData.newPlainText(Constants.COPY_LABEL,stationCodeData.getStationCode());
                    try {
                        clipboardManager.setPrimaryClip(clipData);
                    }
                    catch (NullPointerException e){
                        Messages.toastMessage(context.getApplicationContext(),Constants.ERROR_MESSAGE_GENERAL,"");
                    }
                    Messages.toastMessage(context.getApplicationContext(),Constants.COPIED_TEXT_MESSAGE, "");
                }
            });
        }
        return listView;
    }
}
