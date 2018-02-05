package com.pronoymukherjee.indianrailwaysenquiry.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pronoymukherjee.indianrailwaysenquiry.Constants;
import com.pronoymukherjee.indianrailwaysenquiry.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Filling the HashMap for the Class, Quota and the Response code.
        Constants.fillresponseCodeDesc();
        Constants.fillClassCode();
        Constants.fillQuotaCode();
    }
}
