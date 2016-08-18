package com.example.administrator.fragmentactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.fragment.ToDayNewsFragment;
import com.example.administrator.zhangxsapplication.R;

public class ToDayNewsFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_day_news);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.today_news_fragment,new ToDayNewsFragment())
                .commit();
    }
}
