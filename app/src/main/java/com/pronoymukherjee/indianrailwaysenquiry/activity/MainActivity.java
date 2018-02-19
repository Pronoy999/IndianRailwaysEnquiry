package com.pronoymukherjee.indianrailwaysenquiry.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.pronoymukherjee.indianrailwaysenquiry.Constants;
import com.pronoymukherjee.indianrailwaysenquiry.GridAdapter;
import com.pronoymukherjee.indianrailwaysenquiry.Messages;
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
        GridView gridView=findViewById(R.id.grid);
        GridAdapter gridAdapter=new GridAdapter(getApplicationContext(),Constants.GRID_TEXT_OPTIONS,Constants.GRID_IMAGE_ID);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=null;
                switch (i){
                    case 0:
                        Messages.toastMessage(getApplicationContext(),"Coming Soon","");
                        break;
                    case 1: intent=new Intent(MainActivity.this,TrainFareActivity.class);
                            break;
                    case 2: intent=new Intent(MainActivity.this,PNRActivity.class);
                            break;
                    case 3: intent=new Intent(MainActivity.this,TrainScheduleActivity.class);
                            break;
                    case 4: intent=new Intent(MainActivity.this,StationNameToCode.class);
                            break;
                    case 5: intent=new Intent(MainActivity.this,TrainStatusActivity.class);
                            break;
                }
                if(intent!=null){
                    startActivity(intent);
                }
            }
        });
    }
}
