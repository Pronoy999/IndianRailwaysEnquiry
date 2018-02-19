package com.pronoymukherjee.indianrailwaysenquiry.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.pronoymukherjee.indianrailwaysenquiry.Constants;
import com.pronoymukherjee.indianrailwaysenquiry.Messages;
import com.pronoymukherjee.indianrailwaysenquiry.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class TrainFareActivity extends AppCompatActivity {
    EditText sourceStation,destinationStation,trainNumber,journeyDate,age;
    Spinner classFare,quota;
    String _classFare,_classQuota;


    public interface fareInterface{
        void sendResponse(JSONObject responseObject);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_fare);
        initializeViews();
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            if (!bundle.getString(Constants.TRAIN_NUMBER).equals("")) {
                trainNumber.setText(bundle.getString(Constants.TRAIN_NUMBER));
            }
        }
        ArrayList<String> quotaList=new ArrayList<>();
        quotaList.add("Quota:");//Setting the label of the spinner.
        for(Map.Entry<String,String> entry:Constants.QUOTA_CODE.entrySet()){
            quotaList.add(entry.getKey());
        }
        ArrayList<String> classList=new ArrayList<>();
        classList.add("Class:");// setting the label of the spinner.
        for(Map.Entry<String,String> entry:Constants.CLASS_CODE.entrySet()){
            classList.add(entry.getKey());
        }
        final ArrayAdapter<String> classSpinnerAdapter=new ArrayAdapter<String>(this,
                R.layout.spinner_item,classList){
            @Override
            public boolean isEnabled(int position) {
                if(position==0){
                    return false;
                }
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view=super.getDropDownView(position,convertView,parent);
                TextView textView=(TextView) view;
                if(position==0){
                    textView.setTextColor(Color.GRAY);
                }
                else{
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        classSpinnerAdapter.setDropDownViewResource(R.layout.spinner_item);
        classFare.setAdapter(classSpinnerAdapter);
        classFare.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0) {
                    _classFare = (String) adapterView.getItemAtPosition(i);
                    Messages.toastMessage(getApplicationContext(),"Class Selected: "+_classFare,"");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Messages.toastMessage(getApplicationContext(),"Please select a Class.","");
            }
        });
        final ArrayAdapter<String> quotaAdapter=new ArrayAdapter<String>(this,R.layout.spinner_item,quotaList){
            @Override
            public boolean isEnabled(int position) {
                if(position==0)
                    return false;
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view=super.getDropDownView(position,convertView,parent);
                TextView textView=(TextView) view;
                if(position==0){
                    textView.setTextColor(Color.GRAY);
                }
                else{
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        quotaAdapter.setDropDownViewResource(R.layout.spinner_item);
        quota.setAdapter(quotaAdapter);
        quota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0) {
                    _classQuota = (String) adapterView.getItemAtPosition(i);
                    Messages.toastMessage(getApplicationContext(),"Quota Selected: "+_classQuota,"");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Messages.toastMessage(getApplicationContext(),"Please select a Quota.","");
            }
        });
        //TODO: Make the Edit Text Date Journey.
    }
    private void initializeViews(){
        sourceStation=findViewById(R.id.sourceStation);
        destinationStation=findViewById(R.id.destinationStation);
        trainNumber=findViewById(R.id.trainNumberFare);
        journeyDate=findViewById(R.id.date);
        classFare=findViewById(R.id.classFare);
        quota=findViewById(R.id.quotaFare);
        age=findViewById(R.id.ageFare);
    }
    private void getFare(){

    }
}
