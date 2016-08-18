package com.example.administrator.zhangxsapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    public Button mIntentDialButton,mIntentCallButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        mIntentDialButton = (Button) findViewById(R.id.intent_dial_btn);
        mIntentDialButton.setOnClickListener(this);
        mIntentCallButton = (Button) findViewById(R.id.intent_call_btn);
        mIntentCallButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Uri uri = Uri.parse("tel:01234567890");
        switch (v.getId()){
            case R.id.intent_dial_btn:
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.intent_call_btn:
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(uri);
                startActivity(intent);
                break;
        }
    }
}
