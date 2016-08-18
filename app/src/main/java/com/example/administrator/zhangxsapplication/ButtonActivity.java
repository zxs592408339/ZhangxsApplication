package com.example.administrator.zhangxsapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/5/24.
 */
public class ButtonActivity extends AppCompatActivity implements  View.OnClickListener{
    public TextView mShowMessageTxt;
    public Button mOneConfirmBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        mShowMessageTxt = (TextView) findViewById(R.id.show_message_txt);
        mOneConfirmBtn = (Button) findViewById(R.id.button_name1);
        mOneConfirmBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        mShowMessageTxt.setText("接口实现");

    }

}
