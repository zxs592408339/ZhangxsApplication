package com.example.administrator.zhangxsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.dem.L;

public class MyServiceDownFileActivity extends AppCompatActivity implements View.OnClickListener{
    int count = 0;
    public Button mServiceDownOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service_down_file);
        mServiceDownOne = (Button) findViewById(R.id.service_down_file_one);
        mServiceDownOne.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.service_down_file_one:
                downFile();
                break;
        }
    }
    public void downFile(){
        ++count;
        L.e("下载文件" +count);
        Intent intent = new Intent(this,DownFileService.class);
        startService(intent);
    }
}
