package com.example.administrator.charting.chartingactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.charting.control.TitleView;
import com.example.administrator.zhangxsapplication.R;

public class MyRelativeLayoutActivity extends AppCompatActivity implements TitleView.OnTitleClickListener {

    private TitleView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_relative_layout);
        mTitleView = (TitleView) findViewById(R.id.my_Relative_view);
        mTitleView.setOnTitleClickListener(this);
    }

    @Override
    public void onTitleClick(View view) {
        switch (view.getId()) {
            case R.id.my_img_view_back:
                finish();
                break;
            case R.id.my_text_view_more:
                Toast.makeText(MyRelativeLayoutActivity.this, "更多菜单", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}