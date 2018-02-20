package com.pronoymukherjee.indianrailwaysenquiry.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.pronoymukherjee.indianrailwaysenquiry.Constants;
import com.pronoymukherjee.indianrailwaysenquiry.HTTPConnector;
import com.pronoymukherjee.indianrailwaysenquiry.JsonParser;
import com.pronoymukherjee.indianrailwaysenquiry.Messages;
import com.pronoymukherjee.indianrailwaysenquiry.R;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class TrainFareActivity extends AppCompatActivity implements HTTPConnector.ResponseListener {
    EditText sourceStation,destinationStation,trainNumber,journeyDate,age,numberOfPassenger;
    Spinner classFare,quota;
    String _classFare, _quota;
    Button getFare;
    ProgressBar progressBar;
    TextView fareOne,totalFare;

    Calendar calendar=Calendar.getInstance();

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
        setAdapters();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLable();
            }
        };
        journeyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new  DatePickerDialog(TrainFareActivity.this,dateSetListener,
                        calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        getFare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sourceStation.getText().toString().equals("") || destinationStation.getText().toString().equals("")
                        || age.getText().toString().equals("")
                        || journeyDate.getText().toString().equals("")
                        || classFare.getSelectedItemPosition()==0
                        || quota.getSelectedItemPosition()==0
                        || trainNumber.getText().toString().equals("")){
                    Messages.toastMessage(getApplicationContext(),"Please select all the attributes.","");
                    return;
                }
                getFare();
            }
        });
    }

    private void updateLable(){
        String dateFormat="dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormat, Locale.US);
        journeyDate.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void setAdapters() {
        ArrayList<String> quotaList=new ArrayList<>();
        quotaList.add("Quota:");//Setting the label of the spinner.
        for(Map.Entry<String,String> entry: Constants.QUOTA_CODE.entrySet()){
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
                    _quota = (String) adapterView.getItemAtPosition(i);
                    Messages.toastMessage(getApplicationContext(),"Quota Selected: "+ _quota,"");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Messages.toastMessage(getApplicationContext(),"Please select a Quota.","");
            }
        });
    }

    private void initializeViews(){
        sourceStation=findViewById(R.id.sourceStation);
        destinationStation=findViewById(R.id.destinationStation);
        trainNumber=findViewById(R.id.trainNumberFare);
        journeyDate=findViewById(R.id.date);
        classFare=findViewById(R.id.classFare);
        quota=findViewById(R.id.quotaFare);
        age=findViewById(R.id.ageFare);
        getFare=findViewById(R.id.getFare);
        progressBar=findViewById(R.id.progressBarFare);
        fareOne=findViewById(R.id.fareOne);
        totalFare=findViewById(R.id.totalFare);
        numberOfPassenger=findViewById(R.id.numberofPerson);
    }
    private void getFare(){
        String _source=sourceStation.getText().toString();
        String _destination=destinationStation.getText().toString();
        String _age=age.getText().toString();
        String _trainNumber=trainNumber.getText().toString();
        String _date=journeyDate.getText().toString();
        _classFare=classFare.getSelectedItem().toString();
        _quota =quota.getSelectedItem().toString();
        String url=Constants.TRAIN_FARE_URL;
        String firstParts[]=url.split("<");
        String finalUrl=firstParts[0]+_trainNumber
                +firstParts[1].split(">")[1]
                +_source+"/dest/"
                +_destination+"/age/"
                +_age+"/pref/"
                + _classFare+"/quota/"
                +_quota+"/date/"
                +_date+"/apikey/"
                +Constants.API_KEY;
        HTTPConnector httpConnector=new HTTPConnector(getApplicationContext(),finalUrl,this);
        httpConnector.makeQuery();
    }

    @Override
    public void sendResponse(JSONObject responseObject) {
        JsonParser parser=new JsonParser(responseObject);
        if(parser.isCorrectResponse()){
            Messages.toastMessage(getApplicationContext(),Constants.ERROR_MESSAGE_INTERNET,"");
            return;
        }
        String fare=parser.getFare();
        if(!fare.equals("")){
            fareOne.setText(fare);
        }
        else {
            Messages.toastMessage(getApplicationContext(),Constants.ERROR_MESSAGE_INTERNET,"");
        }
        //TODO(1): Set the TextChangeListener.
    }
}
