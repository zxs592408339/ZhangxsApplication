package com.example.administrator.fragmentactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.fragment.SubListFragment;
import com.example.administrator.zhangxsapplication.R;

public class SubFragmentMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_fragament_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.sub_list_fragment_layout, SubListFragment.newInstance())
                .commit();
    }
}
