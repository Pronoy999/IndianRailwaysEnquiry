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
import com.pronoymukherjee.indianrailwaysenquiry.PnrAdapter;
import com.pronoymukherjee.indianrailwaysenquiry.PnrData;
import com.pronoymukherjee.indianrailwaysenquiry.R;

import java.util.ArrayList;

public class PNRActivity extends AppCompatActivity {
    String TAG=PNRActivity.class.getSimpleName();
    Button getStatusButton;
    EditText pnrEditText;
    ProgressBar progressBar;
    TextView emptyView,pnrNumberShow, dateOfJourney,fromStation, trainNumber,boardingPoint,reservedUpto;
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
        dateOfJourney =findViewById(R.id.dateOfJourneyPnr);
        fromStation=findViewById(R.id.fromStationPnr);
        trainNumber =findViewById(R.id.trainNumber);
        boardingPoint=findViewById(R.id.boardingPointPnr);
        reservedUpto=findViewById(R.id.reservedUptoPnr);
        getStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPnrStatus();
            }
        });
    }
    private void getPnrStatus(){
        String pnrNumber=pnrEditText.getText().toString();
        String splitUrl[]=Constants.PNR_STATUS_URL.split("<");
        String secondPart[]=splitUrl[1].split(">");
        String url=splitUrl[0]+pnrNumber+secondPart[1];
        Messages.logMessage(TAG,url);
        HTTPConnector httpConnector=new HTTPConnector(getApplicationContext(),url,progressBar);
        listView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        httpConnector.makeQuery();
        JsonParser parser=new JsonParser(httpConnector.jsonResponse);
        if(!parser.isCorrectResponse()){
            Messages.toastMessage(getApplicationContext(),Constants.ERROR_MESSAGE_INTERNET,"long");
            return;
        }
        pnrNumberShow.setText(parser.getPnrNumber());
        dateOfJourney.setText(parser.getDateofJourneyPnr());
        fromStation.setText(parser.getFromStation());
        trainNumber.setText(parser.getTrainNumber());
        boardingPoint.setText(parser.getBoardingPoint());
        reservedUpto.setText(parser.getReservedUpto());
        String isChartPrepared=parser.getChartStatus()?"Yes":"No";
        String status[][]=parser.getPNRStatus();
        String classCode=parser.getJourneyClassCode();
        ArrayList<PnrData> pnrStatus=new ArrayList<>();
        int totalPassenger=parser.getNumberofPassengers();
        for(int i=0;i<totalPassenger;i++){
            PnrData pnrData=new PnrData(status[i][0],status[i][1],isChartPrepared,classCode);
            pnrStatus.add(pnrData);
        }
        PnrAdapter adapter=new PnrAdapter(PNRActivity.this,pnrStatus);
        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);
    }
}
