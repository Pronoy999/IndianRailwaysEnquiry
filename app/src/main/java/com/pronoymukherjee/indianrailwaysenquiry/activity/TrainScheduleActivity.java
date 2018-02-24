package com.pronoymukherjee.indianrailwaysenquiry.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import org.json.JSONObject;

public class TrainScheduleActivity extends AppCompatActivity implements HTTPConnector.ResponseListener {
    static TextView trainName,trainNumber,trainDays,trainSource,sourcedepartureTime,emptyView;
    static ListView routeList;
    static EditText trainNumberInput;
    ProgressBar progressBar;Button getStatus;
    String TAG=TrainScheduleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule);
        initializeViews();
        getStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTrainSchedule();
            }
        });

    }

    private void initializeViews() {
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
    }

    private void getTrainSchedule(){
        String trainNumber=trainNumberInput.getText().toString();
        String splitUrl[]= Constants.TRAIN_ROUTE_URL.split("<");
        String secondPart[]=splitUrl[1].split(">");
        String url=splitUrl[0]+trainNumber+secondPart[1];
        HTTPConnector httpConnector=new HTTPConnector(getApplicationContext(),url,this);
        routeList.setVisibility(View.GONE);
        routeList.setEmptyView(emptyView);
        progressBar.setVisibility(View.VISIBLE);
        httpConnector.makeQuery();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=new MenuInflater(getApplicationContext());
        inflater.inflate(R.menu.get_fare,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        String trainNumber="";
        if(!trainNumberInput.getText().toString().equals("")){
            trainNumber=trainNumberInput.getText().toString();
        }
        //Getting the Train Number and changing to the Train Fare Activity with this Train Number.
        Bundle bundle=new Bundle();
        bundle.putString(Constants.TRAIN_NUMBER,trainNumber);
        Intent intent;
        switch (item.getItemId()){
            case R.id.changeFareActivity:
                intent=new Intent(TrainScheduleActivity.this,TrainFareActivity.class);
                intent.putExtras(bundle);
                break;
            default: return false;
        }
        startActivity(intent);
        return true;
    }

    @Override
    public void sendResponse(JSONObject responseObject) {
        JsonParser parser=new JsonParser(responseObject);
        progressBar.setVisibility(View.GONE);
        trainNumber.setText(parser.getTrainNumber());
        trainName.setText(parser.getTrainName());
        //TODO(1): Set data to views.
    }
}
