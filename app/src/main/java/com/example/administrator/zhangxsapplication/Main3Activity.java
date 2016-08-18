package com.example.administrator.zhangxsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhanglayout);
    }

    public void onClickOneViewListener(View v){
        Intent intent = new Intent(this,ButtonActivity.class);
        startActivity(intent);
    }

    public void onClickTwoViewListener(View v){
        Intent intent = new Intent(this,StudentActivity.class);
        startActivity(intent);
    }
}
