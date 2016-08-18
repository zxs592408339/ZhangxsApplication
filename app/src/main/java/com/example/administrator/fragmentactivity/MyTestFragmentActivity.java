package com.example.administrator.fragmentactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.fragment.MyTestFragment;
import com.example.administrator.fragment.ShopsFragment;
import com.example.administrator.zhangxsapplication.R;

public class MyTestFragmentActivity extends AppCompatActivity implements View.OnClickListener {
    public MyTestFragment mMyTestFragment;
    public Button mAddFragmentBrn, mRemoveFragmentBrn, mReplaceFragmentBrn, mFragmentBrn;
    public EditText mMyTestEditTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test_fragment);

        mAddFragmentBrn = (Button) findViewById(R.id.add_fragment_brn);
        mRemoveFragmentBrn = (Button) findViewById(R.id.remove_fragment_btn);
        mReplaceFragmentBrn = (Button) findViewById(R.id.replace_fragment_btn);
        mFragmentBrn = (Button) findViewById(R.id.fragment_btn);
        mMyTestEditTxt = (EditText) findViewById(R.id.life_fragment_edit_txt);

        mAddFragmentBrn.setOnClickListener(this);
        mRemoveFragmentBrn.setOnClickListener(this);
        mReplaceFragmentBrn.setOnClickListener(this);
        mFragmentBrn.setOnClickListener(this);

        mMyTestFragment = new MyTestFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_fragment_brn:
                addFragment();
                break;
            case R.id.remove_fragment_btn:
                removeFragment();
                break;
            case R.id.replace_fragment_btn:
                replaceFragment();
                break;
        }
    }

    public void addFragment() {
        getSupportFragmentManager().beginTransaction().
                add(R.id.life_fragment_layout, mMyTestFragment).
                commit();
    }

    public void removeFragment() {
        getSupportFragmentManager().beginTransaction().
                remove(mMyTestFragment).
                commit();
    }

    public void replaceFragment() {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.life_fragment_layout, new ShopsFragment()).
                commit();
    }
}
