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
import com.pronoymukherjee.indianrailwaysenquiry.Messages;
import com.pronoymukherjee.indianrailwaysenquiry.R;
import com.pronoymukherjee.indianrailwaysenquiry.TrainScheduleAdapter;
import com.pronoymukherjee.indianrailwaysenquiry.TrainScheduleData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrainScheduleActivity extends AppCompatActivity implements HTTPConnector.ResponseListener {
    TextView trainName,trainNumber,trainDays,trainSource, trainDestination,emptyView,arrow,trainDaysShow;
    ListView routeList;
    EditText trainNumberInput;
    ProgressBar progressBar;Button getStatus;
    String TAG=TrainScheduleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule);
        setTitle(getResources().getString(R.string.trainSchedule));
        initializeViews();
        routeList.setEmptyView(emptyView);
        arrow.setVisibility(View.GONE);
        trainDaysShow.setVisibility(View.GONE);
        getStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!trainNumberInput.getText().toString().equals(""))
                    routeList.setEmptyView(emptyView);
                    arrow.setVisibility(View.GONE);
                    getTrainSchedule();
            }
        });
    }

    private void initializeViews() {
        trainName=findViewById(R.id.trainName);
        trainNumber=findViewById(R.id.trainNumber);
        trainDays=findViewById(R.id.trainDays);
        trainSource=findViewById(R.id.trainSource);
        trainDestination =findViewById(R.id.trainDestination);
        emptyView=findViewById(R.id.emptyView);
        routeList=findViewById(R.id.routeList);
        trainNumberInput=findViewById(R.id.trainNumberInput);
        progressBar=findViewById(R.id.progessBar);
        getStatus=findViewById(R.id.getStatus);
        arrow=findViewById(R.id.arrow);
        trainDaysShow=findViewById(R.id.trainDaysShow);
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
        if(!parser.isCorrectResponse()){
            Messages.toastMessage(getApplicationContext(),Constants.ERROR_MESSAGE_INTERNET,"");
            return;
        }
        progressBar.setVisibility(View.GONE);
        arrow.setVisibility(View.VISIBLE);
        trainNumber.setText(parser.getTrainNumber());
        trainName.setText(parser.getTrainName());
        trainDaysShow.setVisibility(View.VISIBLE);
        setData(parser);
    }

    private void setData(JsonParser parser){
        JSONArray days=parser.getDays();
        StringBuilder builder=new StringBuilder();
        builder.append("\t");
        int l=days.length(),i;
        for(i=0;i<l;i++){
            if(parser.isRunningOnDay(i,days)){
                try {
                    String status = days.getJSONObject(i).getString(Constants.TRAIN_DAYS_RUNS_TS);
                    if (status.equals("Y")) {
                        builder.append(days.getJSONObject(i).getString(Constants.STATION_CODE).substring(0,2));
                        builder.append("  ");
                    }
                }
                catch (JSONException e){
                    Messages.logMessage(TAG,e.toString());
                }
            }
        }
        trainDays.setText(builder.toString());
        JSONArray route=parser.getRoute();
        JSONObject station=parser.getStationTrainSchedule(0);
        String sourceStationName=parser.getStationName(station);
        String schDept=parser.getScheduleDeparture(0,route);
        StringBuilder _display=new StringBuilder(sourceStationName);
        _display.append("\n"+schDept);
        trainSource.setText(_display.toString());
        station=parser.getStationTrainSchedule(route.length()-1);
        String destStationName=parser.getStationName(station);
        String schArr=parser.getScheduledArrival(route.length()-1,route);
        _display=new StringBuilder(destStationName+"\n"+schArr);
        trainDestination.setText(_display.toString());
        ArrayList<TrainScheduleData> scheduleData=new ArrayList<>();
        for(i=0;i<route.length();i++){
            String stationName=parser.getStationName(parser.getStationTrainSchedule(i));
            String schArrival=parser.getScheduledArrival(i,route);
            String schDepture=parser.getScheduleDeparture(i,route);
            int halt=Integer.parseInt(parser.getScheduleHalt(i,route));
            int day=Integer.parseInt(parser.getScheduleDay(i,route));
            double distance=Double.parseDouble(parser.getScheduleDistance(i,route));
            TrainScheduleData data=new TrainScheduleData(stationName,
                    schArrival,schDepture,halt,day,distance);
            scheduleData.add(data);
        }
        TrainScheduleAdapter adapter=new TrainScheduleAdapter(TrainScheduleActivity.this,scheduleData);
        routeList.setAdapter(adapter);
        routeList.setVisibility(View.VISIBLE);
    }
}
