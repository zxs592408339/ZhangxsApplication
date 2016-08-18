package com.example.administrator.zhangxsapplication;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.dem.L;

public class MyServiceActivity extends AppCompatActivity implements View.OnClickListener{

    public Button mServiceStartBrn,mServiceStopBrn, mServiceBindStartBrn, mServiceBindCountBrn;
    public TextView mBindServiceTxt;
    private MyService.ICountService iCountService;
    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            L.e("onServiceConnected >>>>>>>>>>");
            iCountService = (MyService.ICountService) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
        mServiceStartBrn = (Button) findViewById(R.id.service_start_brn);
        mServiceStopBrn = (Button) findViewById(R.id.service_stop_brn);
        mServiceBindStartBrn = (Button) findViewById(R.id.service_bind_start_brn);
        mServiceBindCountBrn = (Button) findViewById(R.id.service_bind_count_brn);

        mBindServiceTxt = (TextView) findViewById(R.id.bind_service_txt);

        mServiceStartBrn.setOnClickListener(this);
        mServiceStopBrn.setOnClickListener(this);
        mServiceBindStartBrn.setOnClickListener(this);
        mServiceBindCountBrn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.service_start_brn:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
           case R.id.service_stop_brn:
               Intent stopIntent = new Intent(this, MyService.class);
               stopService(stopIntent);
                break;
           case R.id.service_bind_start_brn:
               Intent binderIntent = new Intent(this, MyService.class);
               bindService(binderIntent,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.service_bind_count_brn:
                getCount();
                break;
        }
    }

    public void getCount(){
        int count = iCountService.getCount();
        mBindServiceTxt.setText(String.valueOf(count));
    }
}
