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
import com.pronoymukherjee.indianrailwaysenquiry.R;

public class TrainScheduleActivity extends AppCompatActivity {
    TextView trainName,trainNumber,trainDays,trainSource,sourcedepartureTime,emptyView;
    ListView routeList;
    EditText trainNumberInput;
    ProgressBar progressBar;Button getStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule);
        trainName=findViewById(R.id.trainName);
        trainNumber=findViewById(R.id.trainNumber);
        trainDays=findViewById(R.id.trainDays);
    trainSource=findViewById(R.id.trainSource);
        sourcedepartureTime=findViewById(R.id.sourcedepartureTime);
        emptyView=findViewById(R.id.emptyView);
        routeList=findViewById(R.id.routeList);
        trainNumberInput=findViewById(R.id.trainNumberInput);
        progressBar=findViewById(R.id.progessBar);
        getStatus=findViewById(R.id.getStatus);
        getStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTrainSchedule();
            }
        });

    }
    private void getTrainSchedule()
    {
        String trainNumber=trainNumberInput.getText().toString();
        String splitUrl[]= Constants.TRAIN_ROUTE_URL.split("<");
        String secondPart[]=splitUrl[1].split(">");
        String url=splitUrl[0]+trainNumber+secondPart[1];
        HTTPConnector httpConnector=new HTTPConnector(getApplicationContext(),url);
        routeList.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        JsonParser parser=new JsonParser(httpConnector.getJsonResponse());


    }
}
