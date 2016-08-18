package com.example.administrator.fragmentactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.fragment.NewsContentFragment;
import com.example.administrator.fragment.NewsListFragment;
import com.example.administrator.zhangxsapplication.R;

public class ArgumentActivity extends AppCompatActivity implements NewsListFragment.OnNewsItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argument);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.news_title_fragment,NewsListFragment.newInstance())
                .commit();
    }

    @Override
    public void onItemClick(String message) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.news_content_fragment, NewsContentFragment.newInstance(message))
                .commit();
    }
}
