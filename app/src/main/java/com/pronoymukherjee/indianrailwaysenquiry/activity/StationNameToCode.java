package com.pronoymukherjee.indianrailwaysenquiry.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pronoymukherjee.indianrailwaysenquiry.Constants;
import com.pronoymukherjee.indianrailwaysenquiry.HTTPConnector;
import com.pronoymukherjee.indianrailwaysenquiry.JsonParser;
import com.pronoymukherjee.indianrailwaysenquiry.R;
import com.pronoymukherjee.indianrailwaysenquiry.StationCodeAdapter;
import com.pronoymukherjee.indianrailwaysenquiry.StationCodeData;

import java.util.ArrayList;

public class StationNameToCode extends AppCompatActivity {
    String TAG=StationNameToCode.class.getSimpleName();
    EditText _stationName;
    Button _getStationCodes;
    static ListView listView;
    ProgressBar progressBar;
    TextView _emptyView;
    static HTTPConnector httpConnector;
    static Activity stationNameToCodeContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_name_to_code);
        setTitle(TAG);
        stationNameToCodeContext=StationNameToCode.this;
        _stationName=findViewById(R.id.stationName);
        _getStationCodes=findViewById(R.id.getStationCode);
        _emptyView=findViewById(R.id.emptyViewStationCode);
        listView=findViewById(R.id.stationCodeList);
        listView.setEmptyView(_emptyView);
        progressBar=findViewById(R.id.progessBarStationCode);
        _getStationCodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStationCodes();
            }
        });
    }
    private void setStationCodes(){
        String urlParts[]= Constants.STATION_NAME_CODE_URL.split("<");
        String urlSecondPart[]=urlParts[1].split(">");
        String stationNameInput=_stationName.getText().toString();
        String finalUrl=urlParts[0]+stationNameInput+urlSecondPart[1];
        httpConnector=new HTTPConnector(getApplicationContext(),finalUrl,progressBar);
        httpConnector.makeQuery();
    }
    public static void updateStatus(){
        JsonParser parser=new JsonParser(httpConnector.jsonResponse);
        String stationNameCode[][]=parser.getStationCodes();
        ArrayList<StationCodeData> arrayList=new ArrayList<>();
        for(int i=0;i<stationNameCode.length;i++){
            StationCodeData  data=new StationCodeData(stationNameCode[i][0],stationNameCode[i][1]);
            arrayList.add(data);
        }
        StationCodeAdapter adapter=new StationCodeAdapter(stationNameToCodeContext,arrayList);
        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);
    }
}
