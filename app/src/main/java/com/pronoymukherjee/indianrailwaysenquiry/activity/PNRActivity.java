package com.pronoymukherjee.indianrailwaysenquiry.activity;

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
import com.pronoymukherjee.indianrailwaysenquiry.Messages;
import com.pronoymukherjee.indianrailwaysenquiry.R;

public class PNRActivity extends AppCompatActivity {
    String TAG=PNRActivity.class.getSimpleName();
    Button getStatusButton;
    EditText pnrEditText;
    ProgressBar progressBar;
    TextView emptyView,pnrNumberShow,dateOfJounrey,fromStation,toStation,boardingPoint,reservedUpto;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr);
        getStatusButton=findViewById(R.id.enterPNR);
        pnrEditText=findViewById(R.id.pnrNumberInput);
        progressBar=findViewById(R.id.progessBar);
        emptyView=findViewById(R.id.emptyView);
        listView=findViewById(R.id.passengerList);
        pnrNumberShow=findViewById(R.id.pnrNumberShow);
        dateOfJounrey=findViewById(R.id.dateOfJourneyPnr);
        fromStation=findViewById(R.id.fromStationPnr);
        toStation=findViewById(R.id.toStationPnr);
        boardingPoint=findViewById(R.id.boardingPointPnr);
        reservedUpto=findViewById(R.id.reservedUptoPnr);
        getStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPnrStaus();
            }
        });
    }
    private void getPnrStaus(){
        String pnrNumber=pnrEditText.getText().toString();
        String splitUrl[]=Constants.PNR_STATUS_URL.split("<");
        String secondPart[]=splitUrl[1].split(">");
        String url=splitUrl[0]+pnrNumber+secondPart[1];
        Messages.logMessage(TAG,url);
        HTTPConnector httpConnector=new HTTPConnector(getApplicationContext(),url);
        listView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        JsonParser parser=new JsonParser(httpConnector.getJsonResponse());
        //TODO(1):Add the other methods of parser.
        //TODO(2): Set the Progress bar to GONE.
    }
}
